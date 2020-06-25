package com.company;

import com.company.buildings.Farm;

import java.util.List;
import java.util.Scanner;

import static com.company.Other.theYear;
import static com.company.Other.weekOfTheYear;

public class View {
    private Scanner scanner;

    public View() {
        scanner = new Scanner(System.in);
    }

    public int mainMenu() {
        System.out.println("Menu:");
        System.out.println("1.  Zakup farmy.\n2.  Zakup ziemi uprawnej.\n3.  Sprzedaz ziemi uprawnej.\n" +
                "4.  Zakup budynkow.\n5.  Zakup zwierzat lub roslin.\n6.  Posadzenie roslin.\n" +
                "7.  Zbiory roslin.\n8.  Sprzedaz zwierzat lub roslin.\n9.  Sprawdzenie stanu zapasow.\n" +
                "10. Szczegoly gospodarstwa.\n0.  Zakonczenie tygodnia.");

        return scanner.nextInt();
    }

    public void printGameInfo(int week, Player player) {
        System.out.println("-------------------------------------------------------");
        System.out.println("Calkowita liczba tygodni: " + week + "                " + player.getCash() + " zl");
        System.out.println("Tydzien " + weekOfTheYear(week) + " roku " + theYear(week) +
                "                 Posiadane farmy: " + player.getFarmList().size());
        System.out.println("-------------------------------------------------------");
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

    public void printList(List<?> list) {
        printList(list, false);
    }

    public int printList(List<?> list, boolean choose) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ".\t" + list.get(i));
        }

        if (choose) {
            System.out.println("Podaj numer lub 0 aby anulowac");
            return getInteger(0, list.size()) - 1;
        }

        return 0;
    }
}
