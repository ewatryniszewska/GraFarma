package com.company;

import com.company.buildings.Farm;

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
        int selectedOption;

        while (true) {
            view.printGameInfo(week, player);
            switch (view.mainMenu()) {
                case 1:
                    System.out.println("Lista farm do kupienia: ");

                    selectedOption = view.printFarms(farmsToOffer, true);
                    if (selectedOption >= 0) {
                        int farmPrice = farmsToOffer.get(selectedOption).farmValue();
                        if (player.getCash() >= farmPrice) {
                            player.getFarmList().add(farmsToOffer.get(selectedOption));
                            player.subtractCash(farmPrice);
                            farmsToOffer.set(selectedOption, generator.generateFarm());
                            System.out.println("Kupiles farme " + (selectedOption + 1));
                        } else {
                            System.out.println("Masz za malo gotowki, by kupic te farme.");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Wybierz farme do ktorej chcesz dokupic ziemie: ");
                    selectedOption = view.printFarms(player.getFarmList(), true);
                    if (selectedOption >= 0) {
                        System.out.println("Ile hektarow chcesz kupic? (" + HECTARE_PRICE + " zl/ha)");
                        int ha = view.getInteger(0);
                        if (player.getCash() >= ha * HECTARE_PRICE) {
                            player.getFarmList().get(selectedOption).addLandArea(ha);
                            player.subtractCash(ha * HECTARE_PRICE);
                            System.out.println("Zakupiono " + ha + " ha do farmy.");
                        } else {
                            System.out.println("Za malo gotowki zeby kupic tyle ziemi.");
                        }
                    }
                    break;
                case 3:
                    System.out.println("Wybierz farme z ktorej chcesz sprzedac ziemie: ");
                    selectedOption = view.printFarms(player.getFarmList(), true);
                    if (selectedOption >= 0) {
                        Farm selectedFarm = player.getFarmList().get(selectedOption);
                        System.out.println("Ile hektarow chcesz kupic? (" + HECTARE_PRICE * VALUE_LOSS + " zl/ha)");
                        int ha = view.getInteger(0, selectedFarm.getLandArea());
                        selectedFarm.subtractLandArea(ha);
                        player.addCash((int) (ha * HECTARE_PRICE * VALUE_LOSS));
                        System.out.println("Sprzedano " + ha + " ha z farmy.");
                    }
                    break;
                case 4:
                    System.out.println("Wybrano 4");
                    break;
                case 5:
                    System.out.println("Wybrano 5");
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
