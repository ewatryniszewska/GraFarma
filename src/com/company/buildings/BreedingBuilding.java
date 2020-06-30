package com.company.buildings;

import com.company.items.Animal;
import com.company.items.AnimalsSpecies;
import com.company.items.AnimalsSummary;
import com.company.items.Species;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BreedingBuilding extends Building {
    private int maxNumberOfAnimals;
    private List<Animal> animalList;

    public BreedingBuilding(int price, int maxNumberOfAnimals) {
        name = "Budynek hodowlany";
        this.price = price;
        this.maxNumberOfAnimals = maxNumberOfAnimals;
        animalList = new ArrayList<>();
    }

    public int getLeftSpace() {
        return maxNumberOfAnimals - animalList.size();
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    @Override
    public void addItems(Species species, int numberOfItems) throws Exception {
        AnimalsSpecies animal = (AnimalsSpecies) species;
        addAnimal(species, numberOfItems, animal.minSellAge);
    }

    public void addAnimal(Species species, int numberOfItems, int age) throws Exception {
        if (!(species instanceof AnimalsSpecies)) {
            throw new Exception("Ten budynek moze przechowywac jedynie zwierzeta!");
        }

        for (int i = 0; i < numberOfItems; i++) {
            if (getLeftSpace() > 0) {
                animalList.add(new Animal((AnimalsSpecies) species, age));
            } else {
                return;
            }
        }
    }

    public Map<AnimalsSpecies, AnimalsSummary> getAnimalsSummary() {
        Map<AnimalsSpecies, AnimalsSummary> map = new HashMap<>();
        for (Animal animal : animalList) {
            AnimalsSummary as = map.getOrDefault(animal.getAnimalType(), new AnimalsSummary());
            if (animal.getAge() >= animal.getAnimalType().maturityAge) {
                as.matureSum++;
            } else if (animal.getAge() >= animal.getAnimalType().minSellAge) {
                as.immatureSum++;
            } else {
                as.toYoungSum++;
            }

            if (animal.getAge() > as.maxAge) {
                as.maxAge = animal.getAge();
            }

            if (animal.getAge() < as.minAge) {
                as.minAge = animal.getAge();
            }
            map.put(animal.getAnimalType(), as);
        }
        return map;
    }

    public void subtractAnimals(int numberOfItems, boolean mature) {
        List<Animal> animalsToRemove = new ArrayList<>();
        for (Animal animal : getAnimalList()) {
            if (numberOfItems == 0) {
                break;
            }

            if ((mature && animal.getAge() >= animal.getAnimalType().maturityAge) ||
                    (!mature && animal.getAge() >= animal.getAnimalType().minSellAge
                            && animal.getAge() < animal.getAnimalType().maturityAge)) {
                animalsToRemove.add(animal);
                numberOfItems--;
            }
        }

        for (Animal animal : animalsToRemove) {
            getAnimalList().remove(animal);
        }
    }

    @Override
    public String toString() {
        return name + ";\t" + price + " zl;\t" + maxNumberOfAnimals + " max szt. zwierzat";
    }

    @Override
    public String detailsToString() {
        String str = toString();
        str += "\nObecnie w budynku znajduje się " + animalList.size() + " zwierzat.\n";
        str += "Pozostalo do wykorzystania " + getLeftSpace() + " miejsc.";
        return str;
    }
}
