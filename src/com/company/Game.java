package com.company;

import com.company.buildings.BreedingBuilding;
import com.company.buildings.Building;
import com.company.buildings.Farm;
import com.company.buildings.Warehouse;
import com.company.items.*;

import java.util.*;

import static com.company.Config.*;
import static com.company.Other.*;

public class Game {
    private Player player;

    private int week;
    private List<Farm> farmsToOffer;

    private Generator generator;

    public Game() {
        player = new Player(250000);
        week = 1;
        generator = new Generator();

        // generating potential farms to buy
        farmsToOffer = generator.generateFarms(NUMBER_OF_FARMS_TO_OFFER);
    }

    protected void startGame() {
        System.out.println("Wpisz 'tak', jezeli chcesz rozpoczac gre.");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next();

        if (answer.equals("tak")) {
            gameOn();
        } else if (answer.equals("motherlode")) {
            player.addCash(5000000);
            gameOn();
        }
    }

    protected void gameOn() {
        View view = new View();
        int selectedOption, numberOfBuilding, itemChoice, numberOfItems;
        List<Building> bl;
        Building building;

        while (player.getGameIsOn()) {
            view.printGameInfo(week, player);
            switch (view.mainMenu()) {
                case 1:
                    System.out.println("Lista farm do kupienia: ");

                    selectedOption = view.printFarms(farmsToOffer, true);
                    if (selectedOption < 0) {
                        break;
                    }

                    int farmPrice = farmsToOffer.get(selectedOption).farmValue();
                    if (player.getCash() >= farmPrice) {
                        player.getFarmList().add(farmsToOffer.get(selectedOption));
                        player.subtractCash(farmPrice);
                        farmsToOffer.set(selectedOption, generator.generateFarm());
                        System.out.println("Kupiles farme " + (selectedOption + 1));
                    } else {
                        System.out.println("Masz za malo gotowki, by kupic te farme.");
                    }
                    break;
                case 2:
                    System.out.println("Wybierz farme do ktorej chcesz dokupic ziemie: ");
                    selectedOption = view.printFarms(player.getFarmList(), true);
                    if (selectedOption < 0) {
                        break;
                    }

                    System.out.println("Ile hektarow chcesz kupic? (" + HECTARE_PRICE + " zl/ha)");
                    numberOfItems = view.getInteger(0);
                    if (player.getCash() >= numberOfItems * HECTARE_PRICE) {
                        player.getFarmList().get(selectedOption).addLandArea(numberOfItems);
                        player.subtractCash(numberOfItems * HECTARE_PRICE);
                        System.out.println("Zakupiono " + numberOfItems + " ha do farmy.");
                    } else {
                        System.out.println("Za malo gotowki zeby kupic tyle ziemi.");
                    }
                    break;
                case 3:
                    System.out.println("Wybierz farme z ktorej chcesz sprzedac ziemie: ");
                    selectedOption = view.printFarms(player.getFarmList(), true);
                    if (selectedOption < 0) {
                        break;
                    }

                    Farm selectedFarm = player.getFarmList().get(selectedOption);
                    System.out.println("Ile hektarow chcesz sprzedac? (" + HECTARE_PRICE * VALUE_LOSS + " zl/ha)");
                    numberOfItems = view.getInteger(0, selectedFarm.getLandArea());
                    selectedFarm.subtractLandArea(numberOfItems);
                    player.addCash((int) (numberOfItems * HECTARE_PRICE * VALUE_LOSS));
                    System.out.println("Sprzedano " + numberOfItems + " ha z farmy.");
                    break;
                case 4:
                    System.out.println("Wybierz farme do ktorej chcesz dokupic budynki: ");
                    selectedOption = view.printFarms(player.getFarmList(), true);
                    if (selectedOption < 0) {
                        break;
                    }

                    numberOfBuilding = view.printList(Arrays.asList(BUILDINGS), true);
                    if (numberOfBuilding < 0) {
                        break;
                    }

                    if (player.getCash() >= BUILDINGS[numberOfBuilding].getPrice()) {
                        player.getFarmList().get(selectedOption).addBuilding(BUILDINGS[numberOfBuilding]);
                        player.subtractCash(BUILDINGS[numberOfBuilding].getPrice());
                        System.out.println("Kupiono " + BUILDINGS[numberOfBuilding] + " do farmy.");
                    } else {
                        System.out.println("Za malo gotowki, zeby kupic ten budynek.");
                    }
                    break;
                case 5:
                    System.out.println("Wybierz farme do ktorej chcesz kupic zwierzeta lub rosliny:");
                    selectedOption = view.printFarms(player.getFarmList(), true);
                    if (selectedOption < 0) {
                        break;
                    }

                    System.out.println("Wybierz budynek, do ktorego chcesz kupic zwierzeta lub rosliny:");
                    bl = player.getFarmList().get(selectedOption).getBuildings();
                    numberOfBuilding = view.printList(bl, true);
                    if (numberOfBuilding < 0) {
                        break;
                    }

                    building = bl.get(numberOfBuilding);
                    if (building.getLeftSpace() <= 0) {
                        System.out.println("W wybranym budynku nie ma już miejsca");
                        break;
                    }

                    System.out.println("Wybierz gatunek, ktory chcesz kupic:");
                    List<Species> sl;

                    if (building instanceof BreedingBuilding) {
                        sl = Arrays.asList(AnimalsSpecies.values());
                    } else {
                        sl = Arrays.asList(PlantsSpecies.values());
                    }

                    itemChoice = view.printList(sl, true);
                    if (itemChoice < 0) {
                        break;
                    }

                    if (sl.get(itemChoice).getBuyPrice() > player.getCash()) {
                        System.out.println("Nie masz wystarczającej ilości gotówki, aby zakupić wybrane dobra");
                        break;
                    }

                    System.out.println("Podaj liczbe sztuk/kg:");
                    numberOfItems = view.getInteger(0, Math.min(
                            building.getLeftSpace(),
                            player.getCash() / sl.get(itemChoice).getBuyPrice()));

                    try {
                        building.addItems(sl.get(itemChoice), numberOfItems);
                    } catch (Exception e) {
                        System.out.println("Niestety, cos poszlo nie tak");
                    }

                    player.subtractCash(sl.get(itemChoice).getBuyPrice() * numberOfItems);

                    System.out.println("Zakupiłeś wybrane dobra do budynku!");
                    System.out.println(building.detailsToString());

                    break;
                case 6:
                    System.out.println("Wybierz farme na ktorej chcesz posadzic rosliny:");

                    List<Farm> farmsWithSpace = new ArrayList<>();
                    for (Farm farm : player.getFarmList()) {
                        if (farm.getFreeLandArea() > 0) {
                            farmsWithSpace.add(farm);
                        }
                    }

                    if (farmsWithSpace.size() == 0) {
                        System.out.println("Nie posiadasz farmy z wolnym miejscem do posadzenia.");
                        break;
                    }

                    selectedOption = view.printFarms(farmsWithSpace, true);
                    if (selectedOption < 0) {
                        break;
                    }

                    System.out.println("Wybierz magazyn, z ktorego chcesz wybrac rosliny:");
                    List<Warehouse> warehouses = new ArrayList<>();
                    for (Warehouse warehouse : player.getFarmList().get(selectedOption).getWarehouses()) {
                        if (warehouse.getContainers().size() != 0) {
                            warehouses.add(warehouse);
                        }
                    }

                    if (warehouses.size() == 0) {
                        System.out.println("Wybrany magazyn nie posiada zapasow.");
                        break;
                    }

                    numberOfBuilding = view.printList(warehouses, true);
                    if (numberOfBuilding < 0) {
                        break;
                    }

                    List<Container> containers = new ArrayList<>();
                    for (Container container : warehouses.get(numberOfBuilding).getContainers()) {
                        if (container.getPlantType().canBePlanted &&
                                weekOfTheYear(week) >= container.getPlantType().minPlantWeek &&
                                weekOfTheYear(week) <= container.getPlantType().maxPlantWeek) {
                            containers.add(container);
                        }
                    }

                    if (containers.size() == 0) {
                        System.out.println("Nie ma roslin do posadzenia w tym okresie.");
                        break;
                    }

                    System.out.println("Wybierz rosline ktora chcesz zasadzic:");
                    itemChoice = view.printList(containers, true);
                    if (itemChoice < 0) {
                        break;
                    }

                    Container container = containers.get(itemChoice);
                    int maxHaWeight = container.getUnits() / container.getPlantType().plantingUnitsPerHa;
                    int maxHaMoney = player.getCash() / container.getPlantType().plantingCost;

                    int totalMax = Math.min(Math.min(maxHaMoney, maxHaWeight),
                            player.getFarmList().get(selectedOption).getFreeLandArea());

                    if (totalMax == 0) {
                        System.out.println("Brak gotowki lub zasobu na obsadzenie pola.");
                        break;
                    }

                    System.out.println("Podaj ile hektarow obsadzic:");
                    numberOfItems = view.getInteger(0, totalMax);

                    try {
                        warehouses.get(numberOfBuilding).subtractPlants(container,
                                numberOfItems * container.getPlantType().plantingUnitsPerHa);
                        player.getFarmList().get(selectedOption).sow(getGrowType(container.getPlantType()), numberOfItems);
                        player.subtractCash(numberOfItems * container.getPlantType().plantingCost);

                        System.out.println("Posadzono rosliny.");
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }

                    break;
                case 7:
                    System.out.println("Wybierz farme, z ktorej chcesz zebrac plony:");

                    List<Farm> farmWithCrops = new ArrayList<>();
                    for (Farm farm : player.getFarmList()) {
                        if (farm.getFields().size() > 0) {
                            farmWithCrops.add(farm);
                        }
                    }

                    if (farmWithCrops.size() == 0) {
                        System.out.println("Nie posiadasz zasianych roslin.");
                        break;
                    }

                    selectedOption = view.printFarms(farmWithCrops, true);
                    if (selectedOption < 0) {
                        break;
                    }

                    List<Field> fields = new ArrayList<>();
                    for (Field field : player.getFarmList().get(selectedOption).getFields()) {
                        if (field.getAge() >= field.getPlantType().minCropWeek &&
                                field.getAge() <= field.getPlantType().maxCropWeek) {
                            fields.add(field);
                        }
                    }

                    if (fields.size() == 0) {
                        System.out.println("Brak roslin do zebrania.");
                        break;
                    }

                    System.out.println("Wybierz pole, z ktorego chcesz zebrac plony:");
                    itemChoice = view.printList(fields, true);
                    if (itemChoice < 0) {
                        break;
                    }

                    Field field = fields.get(itemChoice);
                    int cropCost = field.getPlantType().cropCost * field.getNumberOfHectares();

                    if (cropCost > player.getCash()) {
                        System.out.println("Koszt zbioru (" + cropCost + " zl) jest za wysoki.");
                        break;
                    }

                    int cropSize = field.getPlantType().cropYield * field.getNumberOfHectares();

                    List<Warehouse> warehousesWithSpace = new ArrayList<>();
                    for (Warehouse warehouse : player.getFarmList().get(selectedOption).getWarehouses()) {
                        if (warehouse.getLeftSpace() >= cropSize) {
                            warehousesWithSpace.add(warehouse);
                        }
                    }

                    boolean sell = false;

                    if (warehousesWithSpace.size() == 0) {
                        System.out.println("Zaden z magazynow na tej farmie nie ma wystarczajaco duzo miejsca.");
                        System.out.println("Czy chcesz natychmiast sprzedac plony (1) czy anulowac zbior (0)?");
                        if (view.getInteger(0, 1) == 0) {
                            break;
                        }
                        sell = true;
                    }

                    if (!sell) {
                        System.out.println("Czy chcesz natychmiast sprzedac plony (1) czy przechowac w magazynie (2) / (0 aby anulowac)?");
                        int sellOption = view.getInteger(0, 2);
                        if (sellOption == 0) {
                            break;
                        }

                        sell = sellOption == 1;
                    }

                    try {
                        if (!sell) {
                            System.out.println("Wybierz magazyn, w ktorym bedziesz przechowywac zebrane plony:");
                            numberOfBuilding = view.printList(warehousesWithSpace, true);
                            if (numberOfBuilding < 0) {
                                break;
                            }

                            warehousesWithSpace.get(numberOfBuilding).addItems(getCropType(field.getPlantType()), cropSize);
                        } else {
                            player.addCash(getCropType(field.getPlantType()).unitPrice * cropSize);
                        }
                        player.getFarmList().get(selectedOption).crop(field);
                        player.subtractCash(cropCost);
                        System.out.println("Zebrano plony.");
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }

                    break;
                case 8:
                    if (player.getFarmList().size() == 0) {
                        System.out.println("Nie posiadasz farm.");
                        break;
                    }

                    System.out.println("Wybierz farme, z ktorej chcesz sprzedac zwierzeta lub rosliny:");
                    selectedOption = view.printFarms(player.getFarmList(), true);
                    if (selectedOption < 0) {
                        break;
                    }

                    System.out.println("Wybierz budynek, z ktorego chcesz sprzedac zwierzeta lub rosliny:");
                    bl = player.getFarmList().get(selectedOption).getBuildings();
                    numberOfBuilding = view.printList(bl, true);
                    if (numberOfBuilding < 0) {
                        break;
                    }

                    if (bl.get(numberOfBuilding) instanceof BreedingBuilding) {
                        BreedingBuilding bb = (BreedingBuilding) bl.get(numberOfBuilding);
                        Map<AnimalsSpecies, AnimalsSummary> sm = bb.getAnimalsSummary();

                        if (sm.size() == 0) {
                            System.out.println("Brak zwierzat na sprzedaz w wybranym budynku hodowlanym.");
                            break;
                        }

                        System.out.println("Wybierz zwierzeta na sprzedaz:");
                        AnimalsSpecies animalChoice = view.printAnimalsSummary(sm, true);
                        if (animalChoice == null) {
                            break;
                        }

                        System.out.println("Wybierz czy chcesz sprzedac mlode (1) czy dojrzale (2) osobniki " +
                                "(0 aby zrezygnowac):");
                        selectedOption = view.getInteger(0, 2);

                        if (selectedOption == 0) {
                            break;
                        }

                        System.out.println("Ile sztuk chcesz sprzedac?");
                        numberOfItems = view.getInteger(0, selectedOption == 1
                                ? sm.get(animalChoice).immatureSum : sm.get(animalChoice).matureSum);

                        bb.subtractAnimals(numberOfItems, selectedOption == 2);
                        int money = numberOfItems *
                                (selectedOption == 1 ? animalChoice.immaturePrice : animalChoice.maturePrice);
                        player.addCash(money);

                        System.out.println("Sprzedales " + numberOfItems + " sztuk za " + money + " zl.");
                    } else {
                        Warehouse warehouse = (Warehouse) bl.get(numberOfBuilding);
                        if (warehouse.getContainers().size() == 0) {
                            System.out.println("Brak roślin na sprzedaz w wybranym magazynie.");
                            break;
                        }

                        System.out.println("Wybierz rośliny na sprzedaż:");
                        itemChoice = view.printList(warehouse.getContainers(), true);
                        if (itemChoice < 0) {
                            break;
                        }

                        System.out.println("Ile kikogramow chcesz sprzedac?");
                        numberOfItems = view.getInteger(0, warehouse.getContainers().get(itemChoice).getUnits());

                        warehouse.getContainers().get(itemChoice).addWeight(-numberOfItems);
                        int money = numberOfItems * warehouse.getContainers().get(itemChoice).getPlantType().unitPrice;
                        player.addCash(money);

                        System.out.println("Sprzedano " + numberOfItems + " " +
                                warehouse.getContainers().get(itemChoice).getPlantType().unit +
                                " za " + money + " zl.");
                    }
                    break;
                case 9:
                    switch (view.farmsInfoMenu()) {
                        case 1:
                            view.printFarms(player.getFarmList());
                            break;
                        case 2:
                            List<Farm> farmsWithWarehouse = new ArrayList<>();
                            for (Farm farm : player.getFarmList()) {
                                if (farm.getWarehouses().size() != 0) {
                                    farmsWithWarehouse.add(farm);
                                }
                            }

                            if (farmsWithWarehouse.size() == 0) {
                                System.out.println("Nie posiadasz farmy z magazynem.");
                                break;
                            }

                            System.out.println("Wybierz farme, w której chcesz sprawdzic stan zapasow w magazynach:");
                            selectedOption = view.printFarms(farmsWithWarehouse, true);
                            if (selectedOption < 0) {
                                break;
                            }

                            for (Warehouse warehouse : farmsWithWarehouse.get(selectedOption).getWarehouses()) {
                                System.out.println(warehouse.detailsToString());
                                if (warehouse.getContainers().size() != 0) {
                                    System.out.println("W magazynie znajduja sie:");
                                    view.printList(warehouse.getContainers());
                                } else {
                                    System.out.println("Brak roslin w magazynie.");
                                }
                                System.out.println();
                            }
                            break;
                        case 3:
                            if (player.getFarmList().size() == 0) {
                                System.out.println("Nie posiadasz jeszcze zadnej farmy.");
                                break;
                            }

                            for (Farm farm : player.getFarmList()) {
                                System.out.println(farm.detailsToString());
                                if (farm.getFields().size() != 0) {
                                    System.out.println("Na farmie zasiane są:");
                                    view.printList(farm.getFields());
                                } else {
                                    System.out.println("Jeszcze nic nie posadzono na tej farmie.");
                                }
                            }
                            break;
                        case 4:
                            List<Farm> farmsWithBB = new ArrayList<>();
                            for (Farm farm : player.getFarmList()) {
                                if (farm.getBreedingBuildings().size() != 0) {
                                    farmsWithBB.add(farm);
                                }
                            }

                            if (farmsWithBB.size() == 0) {
                                System.out.println("Nie posiadasz farmy z budynkiem hodowlanym.");
                                break;
                            }

                            System.out.println("Wybierz farme, w której chcesz sprawdzic stan zwierzat w budynkach hodowlanych:");
                            selectedOption = view.printFarms(farmsWithBB, true);
                            if (selectedOption < 0) {
                                break;
                            }

                            for (BreedingBuilding breedingBuilding : farmsWithBB.get(selectedOption).getBreedingBuildings()) {
                                System.out.println(breedingBuilding.detailsToString());
                                if (breedingBuilding.getAnimalList().size() != 0) {
                                    System.out.println("W budynku hodowlanym znajduja sie:");
                                    Map<AnimalsSpecies, AnimalsSummary> summary = breedingBuilding.getAnimalsSummary();
                                    for (AnimalsSpecies as : summary.keySet()) {
                                        System.out.println("Zwierzeta gatunku " + as.speciesName + ":");
                                        System.out.println(summary.get(as));
                                        System.out.println();
                                    }
                                } else {
                                    System.out.println("Brak zwierzat w budynku hodowlanym.");
                                }
                                System.out.println();
                            }
                            break;
                    }
                    break;
                case 10:
                    view.printRules();
                    break;
                case 0:
                    // dane do podsumowania
                    int deadAnimals = 0;
                    int deadFields = 0;
                    int eatenFodder = 0;
                    int bornAnimals = 0;
                    int pesticidesCost = 0;
                    int productionGain = 0;
                    int farmsHaSum = 0;
                    Set<AnimalsSpecies> uniqueAnimals = new HashSet<>();
                    Set<PlantsSpecies> uniquePlants = new HashSet<>();

                    // obsluga zwierzat i roslin
                    for (Farm farm : player.getFarmList()) {
                        // farma
                        farmsHaSum += farm.getLandArea();

                        // zwierzeta
                        for (BreedingBuilding bb : farm.getBreedingBuildings()) {
                            List<Animal> tempDeadAnimals = new ArrayList<>();
                            for (Animal animal : bb.getAnimalList()) {
                                // unikalne zwierzeta
                                uniqueAnimals.add(animal.getAnimalType());

                                // starzenie
                                if (!animal.makeOlder()) {
                                    tempDeadAnimals.add(animal);
                                }

                                // jedzenie
                                int howMuchToEat = animal.getAnimalType().fodderPerWeek;
                                for (PlantsSpecies ps : animal.getAnimalType().plantsEaten) {
                                    howMuchToEat = farm.feed(ps, howMuchToEat);
                                }

                                eatenFodder += animal.getAnimalType().fodderPerWeek - howMuchToEat;

                                // czy zwierze chudnie, czy umiera, czy tyje
                                if (howMuchToEat > 0) {
                                    if (!animal.loseWeight()) {
                                        tempDeadAnimals.add(animal);
                                    }
                                } else {
                                    animal.gainWeight();
                                }
                            }

                            // zwierze umiera
                            deadAnimals += tempDeadAnimals.size();
                            for (Animal animal : tempDeadAnimals) {
                                bb.getAnimalList().remove(animal);
                            }

                            Map<AnimalsSpecies, AnimalsSummary> summary = bb.getAnimalsSummary();
                            for (AnimalsSpecies as : summary.keySet()) {
                                // rozmnazanie
                                double p = as.breedingChance;
                                int x = summary.get(as).matureSum;
                                double expected = (1 - Math.pow(1 - p, x)) * x;
                                Random r = new Random();
                                int born = Math.max((int) Math.round(r.nextGaussian() + expected), 0);
                                if (summary.get(as).matureSum >= 2) {
                                    try {
                                        bb.addAnimal(as, born, 0);
                                        bornAnimals = born;
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                // jaja lub mleko
                                if (as.productSellPrice > 0) {
                                    productionGain += summary.get(as).matureSum * as.productSellPrice * as.productionPerWeek;
                                    player.addCash(summary.get(as).matureSum * as.productSellPrice * as.productionPerWeek);
                                }
                            }


                        }

                        // rosliny
                        List<Field> tempDeadFields = new ArrayList<>();
                        for (Field f : farm.getFields()) {
                            // unikalne zasiane rosliny
                            uniquePlants.add(f.getPlantType());

                            // rosna
                            if (!f.makeOlder()) {
                                tempDeadFields.add(f);
                            }

                            // ochrona roslin
                            if (player.getCash() < f.getPlantType().pestProtectionCost * f.getNumberOfHectares()) {
                                double death = Math.random();
                                if (death < 0.1) {
                                    tempDeadFields.add(f);
                                }
                            } else {
                                pesticidesCost += f.getPlantType().pestProtectionCost * f.getNumberOfHectares();
                                player.subtractCash(f.getPlantType().pestProtectionCost * f.getNumberOfHectares());
                            }
                        }

                        // pole obumiera
                        deadFields += tempDeadFields.size();
                        for (Field f : tempDeadFields) {
                            farm.getFields().remove(f);
                        }
                    }

                    System.out.println("Podsumowanie tygodnia " + week + ": ");
                    System.out.println("-------------------------------------------------------------");
                    System.out.println("Zdechlo:                            " + deadAnimals + " szt. zwierzat");
                    System.out.println("Obumarlo                            " + deadFields + " pol uprawnych");
                    System.out.println("Zwierzeta zjadly:                   " + eatenFodder + " kg paszy");
                    System.out.println("Urodzilo sie:                       " + bornAnimals + " szt. zwierzat");
                    System.out.println("Na pestycydy wydano:                " + pesticidesCost + " zl");
                    System.out.println("Zwierzeta wyprodukowaly towary za:  " + productionGain + " zl");
                    System.out.println("-------------------------------------------------------------");
                    System.out.println("Bilans tygodnia:                    " + (productionGain - pesticidesCost) + " zl");

                    System.out.println();
                    System.out.println("Warunki super farmera: ");
                    System.out.println("20 ha ziem:                    " + (farmsHaSum >= 20 ? "SPELNIONO" : "NIE SPELNIONO") + " (" + farmsHaSum + "/20)");
                    System.out.println("5 gatunkow zwierzat:           " + (uniqueAnimals.size() >= 5 ? "SPELNIONO" : "NIE SPELNIONO") + " (" + uniqueAnimals.size() + "/5)");
                    System.out.println("5 gatunkow roslin:             " + (uniquePlants.size() >= 5 ? "SPELNIONO" : "NIE SPELNIONO") + " (" + uniquePlants.size() + "/5)");
                    System.out.println("1 000 000 zl na koncie:        " + (player.getCash() >= 1000000 ? "SPELNIONO" : "NIE SPELNIONO") + " (" + player.getCash() + "/1000000)");

                    if (farmsHaSum >= 20 && uniqueAnimals.size() >= 5 && uniquePlants.size() >= 5 && player.getCash() >= 1000000) {
                        System.out.println();
                        System.out.println("     ___  _______  _______  _______  _______  _______    _______  __   __  _______  _______  ______      _______  _______  ______    __   __  _______  ______    _______  __   __ ");
                        System.out.println("    |   ||       ||       ||       ||       ||       |  |       ||  | |  ||       ||       ||    _ |    |       ||   _   ||    _ |  |  |_|  ||       ||    _ |  |       ||  |_|  |");
                        System.out.println("    |   ||    ___||  _____||_     _||    ___||  _____|  |  _____||  | |  ||    _  ||    ___||   | ||    |    ___||  |_|  ||   | ||  |       ||    ___||   | ||  |    ___||       |");
                        System.out.println("    |   ||   |___ | |_____   |   |  |   |___ | |_____   | |_____ |  |_|  ||   |_| ||   |___ |   |_||_   |   |___ |       ||   |_||_ |       ||   |___ |   |_||_ |   |___ |       |");
                        System.out.println(" ___|   ||    ___||_____  |  |   |  |    ___||_____  |  |_____  ||       ||    ___||    ___||    __  |  |    ___||       ||    __  ||       ||    ___||    __  ||    ___||       |");
                        System.out.println("|       ||   |___  _____| |  |   |  |   |___  _____| |   _____| ||       ||   |    |   |___ |   |  | |  |   |    |   _   ||   |  | || ||_|| ||   |___ |   |  | ||   |___ | ||_|| |");
                        System.out.println("|_______||_______||_______|  |___|  |_______||_______|  |_______||_______||___|    |_______||___|  |_|  |___|    |__| |__||___|  |_||_|   |_||_______||___|  |_||_______||_|   |_|");
                        System.out.println();
                        System.out.println("Jezeli chcesz zakonczyc gre wpisz 1, aby kontynuowac wpisz 0.");
                        if (view.getInteger(0, 1) == 1) {
                            player.endGame();
                        }
                    }

                    // zwiekszenie tygodnia
                    week++;
                    break;
            }
            view.waitForUser();
        }
    }

}
