package com.company;

import com.company.buildings.Farm;

import java.util.List;
import java.util.Scanner;

public class View {
    private Scanner scanner;

    public View() {
        scanner = new Scanner(System.in);
    }

    public int mainMenu() {
        System.out.println("Menu:");
        System.out.println("1. Zakup farmy.\n2. Zakup ziemi uprawnej.\n3. Sprzedaz ziemi uprawnej.\n" +
                "4. Zakup budynkow.\n5. Zakup zwierzat.\n6. Zakup roslin.\n7. Posadzenie roslin.\n" +
                "8. Zbiory roslin.\n9. Sprzedaz roslin.\n10. Sprzedaz zwierzat.\n11. Sprawdzenie stanu zapasow.\n" +
                "12. Przejrzenie informacji o posiadanych zwierzetach.\n13. Przejrzenie informacji o zasadzonych " +
                "roslinach.\n0. Zakonczenie tygodnia");

        return scanner.nextInt();
    }

    public void printGameInfo(int week, Player player) {
        System.out.println("---------------------------------------------");
        System.out.println("Tydzien " + week + "                        " + player.getCash() + " zl");
        System.out.println("                         Posiadane farmy: " + player.getFarmList().size());
        System.out.println("---------------------------------------------");
    }

    public void printFarms(List<Farm> farms) {
        printFarms(farms, false);
    }

    public int printFarms(List<Farm> farms, boolean choose) {
        for (int i = 0; i < farms.size(); i++) {
            System.out.println("-- FARMA " + (i + 1) + " ---------\t\t\t\t cena: " + farms.get(i).farmValue() + " zl");
            System.out.println(farms.get(i) + "\n");
        }

        if (choose) {
            System.out.println("Podaj numer farmy lub 0 aby anulowac");
            return getInteger(0, farms.size()) - 1;
        }

        return 0;
    }

    public int getInteger(int min) {
        return getInteger(min, Integer.MAX_VALUE);
    }

    public int getInteger(int min, int max) {
        int selectedNumber;
        do {
            System.out.print("Podaj liczbe (min " + min);
            if (max < Integer.MAX_VALUE) {
                System.out.print(", max " + max);
            }
            System.out.println(")");
            selectedNumber = scanner.nextInt();
            if (selectedNumber < min || selectedNumber > max) {
                System.out.println("Wprowadzono niepoprawne dane. Sprobuj jeszcze raz.");
            }
        } while (selectedNumber < min || selectedNumber > max);
        return selectedNumber;
    }
}
