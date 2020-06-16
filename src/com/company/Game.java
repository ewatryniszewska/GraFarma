package com.company;

import com.company.buildings.Farm;

import java.util.List;
import java.util.Scanner;

public class Game {
    private Player player;

    private int week;
    private List<Farm> farmsToOffer;

    private Generator generator;

    public Game() {
        player = new Player(100000);

        this.week = 1;

        generator = new Generator();
        // generating potential farms to buy
        farmsToOffer = generator.generateFarms(Config.NUMBER_OF_FARMS_TO_OFFER);
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

        while (true) {
            switch (view.mainMenu()) {
                case 1:
                    System.out.println("Lista farm do kupienia: ");

                    int numberOfOption = view.printFarms(farmsToOffer, true) - 1;
                    if (numberOfOption > 0) {
                        int farmPrice = farmsToOffer.get(numberOfOption).priceFarm();
                        if (player.getCash() >= farmPrice) {
                            player.getFarmList().add(farmsToOffer.get(numberOfOption));
                            player.subtractCash(farmPrice);
                            farmsToOffer.set(numberOfOption, generator.generateFarm());
                        } else {
                            System.out.println("Masz za malo gotowki, by kupic te farme.");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Wybrano 2");
                    break;
                case 3:
                    System.out.println("Wybrano 3");
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
