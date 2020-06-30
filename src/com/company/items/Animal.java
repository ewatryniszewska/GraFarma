package com.company.items;

public class Animal {
    protected AnimalsSpecies animalType;
    protected int age;
    protected int weight;

    public Animal(AnimalsSpecies animalType, int age) {
        this.animalType = animalType;
        this.age = age;
        this.weight = animalType.minWeight;
    }

    public AnimalsSpecies getAnimalType() {
        return animalType;
    }

    public int getAge() {
        return age;
    }

    public boolean makeOlder() {
        age++;
        return getAge() <= getAnimalType().maxAge;
    }

    public boolean loseWeight() {
        int w = weight - animalType.weightGainPerWeek;
        if (w < animalType.minWeight) {
            return false;
        } else {
            weight = w;
            return true;
        }
    }

    public void gainWeight() {
        int w = weight + animalType.weightGainPerWeek;
        weight = Math.min(w, animalType.maxWeight);
    }

    public String toString() {
        return animalType.speciesName + "; " + age + " tyg; " + weight + " kg; ";
    }
}
