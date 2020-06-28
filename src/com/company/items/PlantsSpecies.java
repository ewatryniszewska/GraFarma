package com.company.items;

public enum PlantsSpecies implements Species {
    POTATO("Ziemniak", 100, 100, 10, 10000,
            16, 26, 20, 32,
            100, 2, "Ziemniak", "Ziemniak", true, 10);
//        CARROT("Marchewka", 80, 20, 5000, 10,
//                       20, 20, 30, 20, 32,
//                       100, 2, "Ziemniak", "Ziemniak", true,
//                       10),
//        LETTUCE("Salata", 100, 10, 10000, 8,
//                       12, 16, 26, 20, 32,
//                       100, 2, "Ziemniak", "Ziemniak", true,
//                       10),
//        OAT("Owies", 100, 10, 10000, 8,
//                       12, 16, 26, 20, 32,
//                       100, 2, "Ziemniak", "Ziemniak", true,
//                       10),
//        GRASS("Trawa", 100, 10, 10000, 8,
//                       12, 16, 26, 20, 32,
//                       100, 2, "Ziemniak", "Ziemniak", true,
//                       10);


    public String plantName;
    public int plantingCost;
    public int plantingKgPerHa;
    public int pestProtectionCost;
    public int cropYield;
    public int minPlantWeek;
    public int maxPlantWeek;
    public int minCropWeek;
    public int maxCropWeek;
    public int cropCost;
    public int kgPrice;
    public String growPlant;
    public String cropPlant;
    public boolean canBePlanted;
    public int numberOfCrops;

    PlantsSpecies(String plantName, int plantingCost, int plantingKgPerHa, int pestProtectionCost, int cropYield,
                  int minPlantWeek, int maxPlantWeek, int minCropWeek,
                  int maxCropWeek, int cropCost, int kgPrice, String growPlant, String cropPlant,
                  boolean canBePlanted, int numberOfCrops
    ) {
        this.plantName = plantName;
        this.plantingCost = plantingCost;
        this.plantingKgPerHa = plantingKgPerHa;
        this.pestProtectionCost = pestProtectionCost;
        this.cropYield = cropYield;
        this.minPlantWeek = minPlantWeek;
        this.maxPlantWeek = maxPlantWeek;
        this.minCropWeek = minCropWeek;
        this.maxCropWeek = maxCropWeek;
        this.cropCost = cropCost;
        this.kgPrice = kgPrice;
        this.growPlant = growPlant;
        this.cropPlant = cropPlant;
        this.canBePlanted = canBePlanted;
        this.numberOfCrops = numberOfCrops;
    }

    public String toString() {
        return "Gatunek: " + plantName;
    }

    public String plantInfo() {
        return "Roslina: " + plantName + "\n" +
                "  koszt posadzenia:         " + plantingCost + " zl/ha\n" +
                "  wydajność sadzenia:       " + plantingKgPerHa + " kg/ha\n" +
                "  koszt pestycydow:         " + pestProtectionCost + " zl/ha\n" +
                "  wydajnosc upraw:          " + cropYield + " kg zbiorow/ha" +
                "  mozliwosc obsadzania:     " + "od " + minPlantWeek + " do " + maxPlantWeek + " tygodnia roku\n" +
                "  mozliwosc zbiorow:        " + "od " + minCropWeek + " do " + maxCropWeek + " tygodni od zasiania\n" +
                "  koszt zbioru:             " + cropCost + " zl/ha\n" +
                "  cena sprzedazy:           " + kgPrice + " zl/kg\n" +
                "  zasadzone wyrasta na:     " + growPlant + "\n" +
                "  zbierane:                 " + cropPlant;
    }

    @Override
    public int getBuyPrice() {
        return kgPrice + 1;
    }
}
