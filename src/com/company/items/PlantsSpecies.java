package com.company.items;

import static com.company.Config.YEAR_LEN;

public enum PlantsSpecies implements Species {
    POTATO("Ziemniak", 100, 100, 50, 5000,
            16, 26, 20, 32,
            200, 3, "Ziemniak", "Ziemniak", true, 8, "kg"),
    CARROT_SEED("Nasiona Marchwi", 150, 100, 30, 0,
            7, 15, 0, 0, 0,
            17, "Marchew", null, true, 0, "kg"),
    CARROT("Marchew", 0, 0, 30, 1000,
            0, 0, 16, 24, 150,
            3, null, "Marchew", false, 1, "kg"),
    LETTUCE_SEED("Nasiona Salaty", 130, 150, 0, 0,
            10, 15, 0, 0, 0,
            10, "Salata", null, true,
            0, "kg"),
    LETTUCE("Salata", 0, 0, 60, 500,
            0, 0, 10, 20, 150,
            6, null, "Salata", false,
            1, "kg"),
    OAT("Owies", 250, 400, 100, 10000,
            20, 36, 30, 50, 300,
            1, "Owies", "Owies", true,
            12, "kg"),
    APPLE_SEEDLING("Sadzonka Jabloni", 100, 50, 0, 0,
            5, 20, 0, 0, 0,
            4, "Jablon", null, true,
            0, "szt"),
    APPLE_TREE("Jablon", 0, 0, 700, 3000,
            0, 0, YEAR_LEN - 1, YEAR_LEN + 1, 200,
            0, null, "Jablka", false,
            30, "szt"),
    APPLE("Jablko", 0, 0, 0, 3000,
            0, 0, 0, 0, 0,
            5, null, null, false,
            0, "kg");

    public String plantName;
    public int plantingCost;
    public int plantingUnitsPerHa;
    public int pestProtectionCost;
    public int cropYield;
    public int minPlantWeek;
    public int maxPlantWeek;
    public int minCropWeek;
    public int maxCropWeek;
    public int cropCost;
    public int unitPrice;
    public String growPlant;
    public String cropPlant;
    public boolean canBePlanted;
    public int numberOfCrops;
    public String unit;

    PlantsSpecies(String plantName, int plantingCost, int plantingUnitsPerHa, int pestProtectionCost, int cropYield,
                  int minPlantWeek, int maxPlantWeek, int minCropWeek,
                  int maxCropWeek, int cropCost, int unitPrice, String growPlant, String cropPlant,
                  boolean canBePlanted, int numberOfCrops, String unit
    ) {
        this.plantName = plantName;
        this.plantingCost = plantingCost;
        this.plantingUnitsPerHa = plantingUnitsPerHa;
        this.pestProtectionCost = pestProtectionCost;
        this.cropYield = cropYield;
        this.minPlantWeek = minPlantWeek;
        this.maxPlantWeek = maxPlantWeek;
        this.minCropWeek = minCropWeek;
        this.maxCropWeek = maxCropWeek;
        this.cropCost = cropCost;
        this.unitPrice = unitPrice;
        this.growPlant = growPlant;
        this.cropPlant = cropPlant;
        this.canBePlanted = canBePlanted;
        this.numberOfCrops = numberOfCrops;
        this.unit = unit;
    }

    public String toString() {
        return "Gatunek: " + plantName;
    }

    public String plantInfo() {
        return "Roslina: " + plantName + "\n" +
                "  koszt posadzenia:         " + plantingCost + " zl/ha\n" +
                "  wydajność sadzenia:       " + plantingUnitsPerHa + " " + unit + " /ha\n" +
                "  koszt pestycydow:         " + pestProtectionCost + " zl/ha\n" +
                "  wydajnosc upraw:          " + cropYield + " " + unit + " zbiorow/ha\n" +
                "  mozliwosc obsadzania:     " + "od " + minPlantWeek + " do " + maxPlantWeek + " tygodnia roku\n" +
                "  mozliwosc zbiorow:        " + "od " + minCropWeek + " do " + maxCropWeek + " tygodni od zasiania\n" +
                "  koszt zbioru:             " + cropCost + " zl/ha\n" +
                "  cena sprzedazy:           " + unitPrice + " zl/" + unit + "\n" +
                "  zasadzone wyrasta na:     " + growPlant + "\n" +
                "  zbierane:                 " + cropPlant;
    }

    @Override
    public int getBuyPrice() {
        return unitPrice + 1;
    }
}
