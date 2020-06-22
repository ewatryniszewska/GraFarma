package com.company.buildings;

import com.company.Config.AnimalsSpecies;
import com.company.items.Animal;

import java.util.ArrayList;
import java.util.List;

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

    public void addAnimals(AnimalsSpecies as, int number) {
        for (int i = 0; i < number; i++) {
            animalList.add(new Animal(as));
        }
    }

    @Override
    public String toString() {
        return name + ";\t" + price + " zl;\t" + maxNumberOfAnimals + " max szt. zwierzat";
    }
}
