package com.company.items;

public class Field {
    protected PlantsSpecies plantType;
    protected int age;
    protected int numberOfCrops;
    protected int numberOfHectares;

    public Field(PlantsSpecies plantType, int numberOfHectares) {
        this.plantType = plantType;
        this.numberOfHectares = numberOfHectares;
        this.age = 0;
        this.numberOfCrops = plantType.numberOfCrops;
    }

    public PlantsSpecies getPlantType() {
        return plantType;
    }

    public int getAge() {
        return age;
    }

    public void makeOlder() {
        age++;
    }

    public int getNumberOfCrops() {
        return numberOfCrops;
    }

    public void crop() {
        age = 0;
        numberOfCrops--;
    }

    public int getNumberOfHectares() {
        return numberOfHectares;
    }

    public String toString() {
        return plantType.plantName + "; " + age + " tygodni; pozostalo " + numberOfCrops + " zbiorow";
    }
}
