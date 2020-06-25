package com.company;

import com.company.buildings.BreedingBuilding;
import com.company.buildings.Building;
import com.company.buildings.Farm;
import com.company.buildings.Warehouse;
import com.company.items.AnimalsSpecies;
import com.company.items.Container;
import com.company.items.PlantsSpecies;
import com.company.items.Species;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.company.Config.*;
import static com.company.Other.weekOfTheYear;

public class Game {
    private Player player;

    private int week;
    private List<Farm> farmsToOffer;

    private Generator generator;

    public Game() {
        player = new Player(10000000);
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
        } else if (answer.equals("nie")) {
            System.out.println("cos");
        } else {
            System.out.println("Niepoprawne dane, sproboj ponownie.");
        }
    }

    protected void gameOn() {
        View view = new View();
        int selectedOption, numberOfBuilding, itemChoice, numberOfItems;

        while (true) {
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
                    List<Building> bl = player.getFarmList().get(selectedOption).getBuildings();
                    numberOfBuilding = view.printList(bl, true);
                    if (numberOfBuilding < 0) {
                        break;
                    }

                    Building building = bl.get(numberOfBuilding);
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

                    List<Farm> farmWithSpace = new ArrayList<>();
                    for (Farm farm : player.getFarmList()) {
                        if (farm.getFreeLandArea() > 0) {
                            farmWithSpace.add(farm);
                        }
                    }

                    if (farmWithSpace.size() == 0) {
                        System.out.println("Nie posiadasz farmy z wolnym miejscem do posadzenia.");
                    }

                    selectedOption = view.printFarms(farmWithSpace, true);
                    if (selectedOption < 0) {
                        break;
                    }

                    System.out.println("Wybierz magazyn, z ktorego chcesz wybrac rosliny:");
                    List<Warehouse> warehouses = player.getFarmList().get(selectedOption).getWarehouses();
                    numberOfBuilding = view.printList(warehouses, true);
                    if (numberOfBuilding < 0) {
                        break;
                    }

                    System.out.println("Wybierz rosline ktora chcesz zasadzic:");
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

                    itemChoice = view.printList(containers, true);
                    if (itemChoice < 0) {
                        break;
                    }

                    Container container = containers.get(itemChoice);
                    int maxHaWeight = container.getWeight() / container.getPlantType().plantingKgPerHa;
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
                        warehouses.get(numberOfBuilding).subtractPlants(container, numberOfItems);
                        player.getFarmList().get(selectedOption).sow(container.getPlantType(), numberOfItems);
                        player.subtractCash(numberOfItems * container.getPlantType().plantingCost);

                        System.out.println("Posadzono rosliny.");
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }

                    break;
                case 7:
                    System.out.println("Wybrano 7");
                    break;
                case 8:
                    System.out.println("Wybrano 8");
                    break;
                case 9:
                    System.out.println("Wybrano 9");
                    break;
                case 10:
                    System.out.println("Wybrano 10");
                    break;
                case 11:
                    System.out.println("Wybrano 11");
                    break;
                case 12:
                    System.out.println("Wybrano 12");
                    break;
                case 13:
                    System.out.println("Wybrano 13");
                    break;
                case 0:
                    week++;
                    break;
            }
        }
    }

}
