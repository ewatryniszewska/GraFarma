package com.company.buildings;

public class BreedingBuilding extends Building {
    private int maxNumberOfAnimals;

    public BreedingBuilding(int price, int maxNumberOfAnimals) {
        this.name = "Budynek hodowlany";

        this.price = price;
        this.maxNumberOfAnimals = maxNumberOfAnimals;
    }

    public int getMaxNumberOfAnimals() {
        return maxNumberOfAnimals;
    }

    @Override
    public String toString() {
        return name + ";\t" + price + " zl;\t" + maxNumberOfAnimals + " max szt. zwierzat";
    }
}
