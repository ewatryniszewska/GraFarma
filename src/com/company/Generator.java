package com.company;

import com.company.buildings.BreedingBuilding;
import com.company.buildings.Farm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {
    public Farm generateFarm() {
        Farm farm = new Farm();

        int randomNum = ThreadLocalRandom.current().nextInt(0, 4);
        for (int i = 0; i < randomNum; i++) {
            farm.addBuilding(generateBreedingBuilding());
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
        int randomPriceForAnimal = ThreadLocalRandom.current().nextInt(120, 140);
        return new BreedingBuilding(randomPriceForAnimal * randomNumberOfAnimals, randomNumberOfAnimals);
    }
}
