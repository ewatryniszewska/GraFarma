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

    public void printFarms(List<Farm> farms) {
        printFarms(farms, false);
    }

    public int printFarms(List<Farm> farms, boolean choose) {
        for (int i = 0; i < farms.size(); i++) {
            System.out.println("Farma " + (i + 1) + "\t  cena " + farms.get(i).priceFarm()
                    + "\t  zawiera - " + farms.get(i));
        }

        if (choose) {
            System.out.println("Ktora farme wybierasz? Wpisz odpowiednia cyfre: ");
            System.out.println("Chcesz wrocic do menu. Wpisz 0");
            int selectedFarm = scanner.nextInt();
            System.out.println("Kupiles farme " + selectedFarm);
            return selectedFarm;
        }

        return 0;
    }
}
