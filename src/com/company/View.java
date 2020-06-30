package com.company;

import com.company.buildings.Farm;
import com.company.items.AnimalsSpecies;
import com.company.items.AnimalsSummary;
import com.company.items.PlantsSpecies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.company.Other.theYear;
import static com.company.Other.weekOfTheYear;

public class View {
    private Scanner scanner;

    public View() {
        scanner = new Scanner(System.in);
    }

    public String getName(int i) {
        System.out.println("Podaj imie gracza nr " + (i + 1) + ":");
        return scanner.next();
    }

    public int mainMenu() {
        System.out.println("Menu:");
        System.out.println("1.   Zakup farmy.\n2.   Zakup ziemi uprawnej.\n3.   Sprzedaz ziemi uprawnej.\n" +
                "4.   Zakup budynkow.\n5.   Zakup zwierzat lub roslin.\n6.   Posadzenie roslin.\n" +
                "7.   Zbiory roslin.\n8.   Sprzedaz zwierzat lub roslin.\n9.   Informacje o farmach." +
                "\n10.  Zasady gry.\n123. Zakoncz gre.\n0.   Zakonczenie tygodnia.");

        return scanner.nextInt();
    }

    public int farmsInfoMenu() {
        System.out.println("Menu:");
        System.out.println("1.  Lista farm.\n2.  Sprawdzenie stanu zapasow (magazyny).\n" +
                "3.  Sprawdzenie stanu pol uprawnych.\n" +
                "4.  Sprawdzenie stanu zwierząt (budynki hodowlane).\n0.  Powrot do glownego menu.");

        return scanner.nextInt();
    }

    public void printGameInfo(int week, Player player) {
        System.out.println("-------------------------------------------------------");
        System.out.println("Teraz gra: " + player.getName());
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
        if (farms.size() == 0) {
            System.out.println("Nie posiadasz farm.");
            return 0;
        }

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

    public AnimalsSpecies printAnimalsSummary(Map<AnimalsSpecies, AnimalsSummary> animalsSummary) {
        return printAnimalsSummary(animalsSummary, false);
    }

    public AnimalsSpecies printAnimalsSummary(Map<AnimalsSpecies, AnimalsSummary> animalsSummary, boolean choose) {
        List<AnimalsSpecies> tempSpecies = new ArrayList<>();

        int i = 1;
        for (AnimalsSpecies as : animalsSummary.keySet()) {
            tempSpecies.add(as);
            System.out.println(i + ": " + as.speciesName + ":\t" + animalsSummary.get(as));
            i++;
        }

        if (choose) {
            System.out.println("Podaj numer lub 0 aby anulowac");
            i = getInteger(0, animalsSummary.size()) - 1;
            if (i < 0) {
                return null;
            }
            return tempSpecies.get(i);
        }

        return null;
    }

    public void printRules() {
        System.out.println("Zasady gry:");
        System.out.println("Gra turowa w farmera polega na zarzadzaniu farma.");
        System.out.println("Celem gry jest:");
        System.out.println("  - posiadanie conajmniej 20ha ziemi uprawnej");
        System.out.println("  - posiadanie conajmniej 5 roznych gatunkow zwierzat");
        System.out.println("  - posiadanie conajmniej 5 roznych posadzonych gatunkow roslin");
        System.out.println("  - posiadanie 1 milion zl na koncie");
        System.out.println();
        System.out.println("Pieniadze zarabiac mozesz:");
        System.out.println("  - hodujac, a nastepnie sprzedajac zwierzeta lub ich produkty");
        System.out.println("  - uprawiajac, a nastepnie sprzedajac rosliny");
        System.out.println();
        System.out.println("W grze dostepne sa nastepujace gatunki roslin:");
        for (PlantsSpecies ps : PlantsSpecies.values()) {
            System.out.println(ps.plantInfo() + "\n");
        }
        System.out.println("W grze dostepne sa nastepujace gatunki zwierzat:");
        for (AnimalsSpecies as : AnimalsSpecies.values()) {
            System.out.println(as.speciesInfo() + "\n");
        }
        System.out.println("Akcje pod koniec tygodnia:");
        System.out.println("  - zwierzeta: starzeja sie, zjadaja pokarm, przybieraja na wadze, daja produkty, rozmnazaja sie");
        System.out.println("  - rosliny: rosna, ponosisz koszty ochrony roslin przed szkodnikami");
        System.out.println("Jezeli zabraknie paszy dla zwierzat - zwierzeta chudna i moga umrzec.");
        System.out.println("Jezeli zabraknie pieniedzy na srodki ochrony roslin - uprawy moga zostac zniszczone.");
        System.out.println();
        System.out.println("UDANEJ ROZGRYWKI!");
    }

    public void waitForUser() {
        System.out.println("Wcisnij enter aby przejsc dalej.");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
