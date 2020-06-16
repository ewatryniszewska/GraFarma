package com.company;

import com.company.buildings.BreedingBuilding;
import com.company.buildings.Farm;
import com.company.buildings.Warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {
    public Farm generateFarm() {
        Farm farm = new Farm();

        int randomNumOfBreeding = ThreadLocalRandom.current().nextInt(0, 4);
        int randomNumOfWarehouse = ThreadLocalRandom.current().nextInt(0, 4);

        for (int i = 0; i < randomNumOfBreeding; i++) {
            farm.addBuilding(generateBreedingBuilding());
        }

        int length = farm.getBuildings().size() + randomNumOfWarehouse;
        for (int i = farm.getBuildings().size(); i < length; i++) {
            farm.addBuilding(generateWarehouse());
        }

        return farm;
    }

    public List<Farm> generateFarms(int numberOfFarms) {
        List<Farm> farmsList = new ArrayList<>();
        for (int i = 0; i < numberOfFarms; i++) {
            farmsList.add(generateFarm());
        }
        return farmsList;
    }

    public BreedingBuilding generateBreedingBuilding() {
        int randomNumberOfAnimals = ThreadLocalRandom.current().nextInt(10, 100);
        int randomPricePerAnimal = ThreadLocalRandom.current().nextInt(120, 140);
        return new BreedingBuilding(randomPricePerAnimal * randomNumberOfAnimals, randomNumberOfAnimals);
    }

    public Warehouse generateWarehouse() {
        int randomFodderWeight = ThreadLocalRandom.current().nextInt(1, 10) * 10000;
        int randomPricePerFodderWeight = ThreadLocalRandom.current().nextInt(1, 2);
        return new Warehouse(randomPricePerFodderWeight * randomFodderWeight, randomFodderWeight);
    }
}
