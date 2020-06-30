package com.company.items;

import java.util.Arrays;
import java.util.List;

import static com.company.Config.YEAR_LEN;

public enum AnimalsSpecies implements Species {
    PIG("Swinia", 15, 6, null, 0,
            null, 0, 1000, 500, 3,
            300, 10, 5 * YEAR_LEN, 40,
            Arrays.asList(PlantsSpecies.POTATO, PlantsSpecies.CARROT, PlantsSpecies.LETTUCE, PlantsSpecies.OAT, PlantsSpecies.APPLE), 0.05),
    COW("Krowa", 30, 10, "mleko", 100000,
            "litr", 4, 3000, 1000, 10,
            1500, 100, 10 * YEAR_LEN, 200, Arrays.asList(PlantsSpecies.LETTUCE, PlantsSpecies.OAT),
            0.1),
    CHICKEN("Kura", 8, 2, "jajko", 100,
            "sztuka", 1, 500, 200, 1,
            50, 5, 2 * YEAR_LEN, 10, Arrays.asList(PlantsSpecies.OAT, PlantsSpecies.CARROT_SEED, PlantsSpecies.LETTUCE_SEED),
            0.2),
    GOAT("Koza", 10, 4, "mleko", 5000,
            "litr", 10, 2000, 800, 4,
            200, 10, 5 * YEAR_LEN, 50, Arrays.asList(PlantsSpecies.LETTUCE, PlantsSpecies.CARROT, PlantsSpecies.OAT),
            0.3),
    HORSE("Kon", 30, 12, null, 0,
            null, 0, 10000, 5000, 10,
            1000, 80, 15 * YEAR_LEN, 150, Arrays.asList(PlantsSpecies.APPLE, PlantsSpecies.CARROT, PlantsSpecies.OAT),
            0.1);

    public int maturityAge;
    public int minSellAge;
    public String speciesName;
    public String productName;
    public int productionPerWeek;
    public String productionUnit;
    public int productSellPrice;
    public int maturePrice;
    public int immaturePrice;
    public int weightGainPerWeek;
    public int maxWeight;
    public int minWeight;
    public int maxAge;
    public int fodderPerWeek;
    public List<PlantsSpecies> plantsEaten;
    protected double breedingChance;

    AnimalsSpecies(String speciesName, int maturityAge, int minSellAge, String productName, int productionPerWeek,
                   String productionUnit, int productSellPrice, int maturePrice, int immaturePrice,
                   int weightGainPerWeek, int maxWeight, int minWeight, int maxAge, int fodderPerWeek,
                   List<PlantsSpecies> plantsEaten, double breedingChance
    ) {
        this.speciesName = speciesName;
        this.maturityAge = maturityAge;
        this.minSellAge = minSellAge;
        this.productName = productName;
        this.productionPerWeek = productionPerWeek;
        this.productionUnit = productionUnit;
        this.productSellPrice = productSellPrice;
        this.maturePrice = maturePrice;
        this.immaturePrice = immaturePrice;
        this.weightGainPerWeek = weightGainPerWeek;
        this.maxWeight = maxWeight;
        this.minWeight = minWeight;
        this.maxAge = maxAge;
        this.fodderPerWeek = fodderPerWeek;
        this.plantsEaten = plantsEaten;
        this.breedingChance = breedingChance;
    }

    public String toString() {
        return "Gatunek: " + speciesName + ";\t\tcena za młode: " + immaturePrice + " zl";
    }

    public String speciesInfo() {
        return "Gatunek: " + speciesName + "\n" +
                "  wiek dojrzalosci:         " + maturityAge + "\n" +
                "  minimalny wiek sprzedazy: " + minSellAge + "\n" +
                "  produkcja:                " + productName + " - " +
                productionPerWeek + " " + productionUnit + "/tydzien\n" +
                "  cena sprzedazy produktu:  " + productSellPrice + " zl/" + productionUnit + "\n" +
                "  cena dojrzalego osobnika: " + maturePrice + " zl\n" +
                "  cena niedojrz. osobnika:  " + immaturePrice + " zl\n" +
                "  przybieranie na wadze:    " + weightGainPerWeek + " kg/tydzien\n" +
                "  maksymalna osiagana waga: " + maxWeight + " kg\n" +
                "  zjadana pasza:            " + fodderPerWeek + " kg/tydzien\n" +
                "  szansa rozmnozenia:       " + breedingChance * 100 + "%";
    }

    @Override
    public int getBuyPrice() {
        return immaturePrice;
    }
}