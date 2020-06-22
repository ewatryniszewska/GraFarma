package com.company;

import com.company.buildings.BreedingBuilding;
import com.company.buildings.Farm;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.company.Config.*;

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

    public void startGame() {
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

    public void gameOn() {
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
                    System.out.println("Wybierz farme do ktorej chcesz kupic zwierzeta: ");
                    selectedOption = view.printFarms(player.getFarmList(), true);
                    if (selectedOption < 0) {
                        break;
                    }

                    System.out.println("Wybierz budynek, do ktorego chcesz kupic zwierzeta: ");
                    List<BreedingBuilding> bb = player.getFarmList().get(selectedOption).getBreedingBuildings();
                    numberOfBuilding = view.printList(bb, true);
                    if (numberOfBuilding < 0) {
                        break;
                    }

                    if (bb.get(numberOfBuilding).getLeftSpace() <= 0) {
                        System.out.println("W wybranym budynku nie ma już miejsca");
                        break;
                    }

                    List<AnimalsSpecies> as = Arrays.asList(AnimalsSpecies.values());
                    itemChoice = view.printList(as, true);
                    if (itemChoice < 0) {
                        break;
                    }

                    if (as.get(itemChoice).immaturePrice > player.getCash()) {
                        System.out.println("Nie masz wystarczającej ilości gotówki, aby zakupić wybrane zwierzęta");
                        break;
                    }

                    numberOfItems = view.getInteger(0, Math.min(
                            bb.get(numberOfBuilding).getLeftSpace(),
                            player.getCash() / as.get(itemChoice).immaturePrice));

                    bb.get(numberOfBuilding).addAnimals(as.get(itemChoice), numberOfItems);
                    player.subtractCash(as.get(itemChoice).immaturePrice * numberOfItems);

                    break;
                case 6:
                    System.out.println("Wybrano 6");
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
                    System.out.println("Wybrano 0");
                    break;
            }
        }
    }

}
