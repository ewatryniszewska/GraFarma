package com.company;

import java.util.Scanner;

public class Game {
    public Double cash = 1000.0;
    public Integer week = 1;

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
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Zakup farmy.\n2. Zakup ziemi uprawnej.\n3. Sprzedaz ziemi uprawnej.\n" +
                    "4. Zakup budynkow.\n5. Zakup zwierzat.\n6. Zakup roslin.\n7. Posadzenie roslin.\n" +
                    "8. Zbiory roslin.\n9. Sprzedaz roslin.\n10. Sprzedaz zwierzat.\n11. Sprawdzenie stanu zapasow.\n" +
                    "12. Przejrzenie informacji o posiadanych zwierzetach.\n13. Przejrzenie informacji o zasadzonych " +
                    "roslinach.\n0. Zakonczenie tygodnia");
            Scanner scanner = new Scanner(System.in);
            int menuNumber = scanner.nextInt();
            switch (menuNumber) {
                case 1:
                    System.out.println("Wybrano 1");
                    System.out.println("Powrot do menu. Wpisz 'e'");
                    String backToMenu = scanner.next();
                    if (!backToMenu.equals("e")) {
                        System.out.println("...");
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
