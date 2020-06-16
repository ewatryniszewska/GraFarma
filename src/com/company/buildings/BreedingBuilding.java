package com.company.buildings;

public class BreedingBuilding {
    public static final String NAME = "Budynek hodowlany";
    private int price;
    private int maxNumberOfAnimals;

    public BreedingBuilding(int price, int maxNumberOfAnimals) {
        this.price = price;
        this.maxNumberOfAnimals = maxNumberOfAnimals;
    }

    public int getPrice() {
        return price;
    }

    public int getMaxNumberOfAnimals() {
        return maxNumberOfAnimals;
    }

    public String toString() {
        return NAME + ";\t" + price + " zl;\t" + maxNumberOfAnimals + " max szt. zwierzat";
    }
}
