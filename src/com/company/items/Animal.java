package com.company.items;

public class Animal {
    protected AnimalsSpecies animalType;
    protected int age;
    protected int weight;

    public Animal(AnimalsSpecies animalType) {
        this.animalType = animalType;
        this.age = animalType.minSellAge;
        this.weight = animalType.minWeight;
    }

    public AnimalsSpecies getAnimalType() {
        return animalType;
    }

    public int getAge() {
        return age;
    }

    public void makeOlder() {
        age++;
    }

    public String toString() {
        return animalType.speciesName + "; " + age + " tyg; " + weight + " kg; ";
    }
}
