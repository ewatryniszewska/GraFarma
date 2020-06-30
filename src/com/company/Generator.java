package com.company;

import com.company.buildings.BreedingBuilding;
import com.company.buildings.Farm;
import com.company.buildings.Warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {
    public Farm generateFarm() {
        int randomLandArea = ThreadLocalRandom.current().nextInt(2, 10);

        Farm farm = new Farm(randomLandArea);

        int randomNumOfBreeding = ThreadLocalRandom.current().nextInt(0, 2);
        int randomNumOfWarehouse = ThreadLocalRandom.current().nextInt(0, 2);

        for (int i = 0; i < randomNumOfBreeding; i++) {
            farm.addBuilding(generateBreedingBuilding());
        }

        for (int i = 0; i < randomNumOfWarehouse; i++) {
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
        int randomNumberOfAnimals = ThreadLocalRandom.current().nextInt(5, 20);
        int randomPricePerAnimal = ThreadLocalRandom.current().nextInt(120, 140);
        return new BreedingBuilding(randomPricePerAnimal * randomNumberOfAnimals, randomNumberOfAnimals);
    }

    public Warehouse generateWarehouse() {
        int randomFodderWeight = ThreadLocalRandom.current().nextInt(1, 10) * 1000;
        int randomPricePerFodderWeight = ThreadLocalRandom.current().nextInt(1, 3);
        return new Warehouse(randomPricePerFodderWeight * randomFodderWeight, randomFodderWeight);
    }
}
