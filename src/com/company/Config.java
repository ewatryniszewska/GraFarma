package com.company;

import com.company.buildings.BreedingBuilding;
import com.company.buildings.Building;
import com.company.buildings.Warehouse;

import java.util.Arrays;
import java.util.List;

public class Config {
    public static final int NUMBER_OF_FARMS_TO_OFFER = 3;
    public static final int HECTARE_PRICE = 30000;
    public static final double VALUE_LOSS = 0.9;
    public static final int YEAR_LEN = 52;

    public enum PlantsSpecies {
        POTATO("Ziemniak", 100, 10, 10000, 8,
                12, 16, 26, 20, 32,
                100, 2000, "Ziemniak", "Ziemniak", true,
                10);

        public String plantName;
        public int plantingCost;
        public int pestProtectionCost;
        public int cropYield;
        public int vegetationMinTime;
        public int vegetationMaxTime;
        public int minPlantWeek;
        public int maxPlantWeek;
        public int minCropWeek;
        public int maxCropWeek;
        public int cropCost;
        public int cropPrice;
        public String growPlant;
        public String cropPlant;
        public boolean canBePlanted;
        public int numberOfCrops;

        PlantsSpecies(String plantName, int plantingCost, int pestProtectionCost, int cropYield, int vegetationMinTime,
                      int vegetationMaxTime, int minPlantWeek, int maxPlantWeek, int minCropWeek, int maxCropWeek, int cropCost,
                      int cropPrice, String growPlant, String cropPlant, boolean canBePlanted, int numberOfCrops
        ) {
            this.plantName = plantName;
            this.plantingCost = plantingCost;
            this.pestProtectionCost = pestProtectionCost;
            this.cropYield = cropYield;
            this.vegetationMinTime = vegetationMinTime;
            this.vegetationMaxTime = vegetationMaxTime;
            this.minPlantWeek = minPlantWeek;
            this.maxPlantWeek = maxPlantWeek;
            this.minCropWeek = minCropWeek;
            this.maxCropWeek = maxCropWeek;
            this.cropCost = cropCost;
            this.cropPrice = cropPrice;
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
                    "  koszt pestycydow:         " + pestProtectionCost + " zl/ha\n" +
                    "  wydajnosc upraw:          " + cropYield + " kg zbiorow/ha" +
                    "  minimalna dl. wegetacji:  " + vegetationMinTime + " tygodni\n" +
                    "  maksymalna dl. wegetacji: " + vegetationMaxTime + " tygodni\n" +
                    "  mozliwosc obsadzania:     " + "od " + minPlantWeek + " do " + maxPlantWeek + " tygodnia roku\n" +
                    "  mozliwosc zbiorow:        " + "od " + minCropWeek + " do " + maxCropWeek + " tygodnia roku\n" +
                    "  koszt zbioru:             " + cropCost + " zl/ha\n" +
                    "  cena sprzedazy:           " + cropPrice + " zl/kg\n" +
                    "  zasadzone wyrasta na:     " + growPlant + "\n" +
                    "  zbierane:                 " + cropPlant;
        }
    }

    public enum AnimalsSpecies {
        PIG("Swinia", 10, 6, null, 0,
                null, 0, 1000, 500, 3,
                300, 10, 5 * YEAR_LEN, 40, Arrays.asList(PlantsSpecies.POTATO),
                0.05);

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
    }

    public static final Building[] BUILDINGS = {
            new BreedingBuilding(50000, 100),
            new BreedingBuilding(100000, 200),
            new BreedingBuilding(200000, 300),
            new BreedingBuilding(500000, 500),
            new BreedingBuilding(1000000, 1000),
            new Warehouse(250000, 100000),
            new Warehouse(500000, 250000),
            new Warehouse(750000, 500000),
            new Warehouse(1500000, 1000000),
            new Warehouse(3000000, 5000000)
    };
//    public static final Warehouse[] WAREHOUSES = {
//
//    }
}
