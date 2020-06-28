package com.company.buildings;

import com.company.items.Animal;
import com.company.items.AnimalsSpecies;
import com.company.items.Species;

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

    public List<Animal> getAnimalList() {
        return animalList;
    }

    @Override
    public void addItems(Species species, int numberOfItems) throws Exception {
        if (!(species instanceof AnimalsSpecies)) {
            throw new Exception("Ten budynek moze przechowywac jedynie zwierzeta!");
        }

        for (int i = 0; i < numberOfItems; i++) {
            animalList.add(new Animal((AnimalsSpecies) species));
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
