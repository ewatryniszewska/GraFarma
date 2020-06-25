package com.company.items;

public class Container {
    protected PlantsSpecies plantType;
    protected int weight;

    public Container(PlantsSpecies plantType, int weight) {
        this.plantType = plantType;
        this.weight = weight;
    }

    public String toString() {
        return plantType.plantName + "; " + weight + " kg";
    }

    public int getWeight() {
        return weight;
    }

    public void addWeight(int weight) {
        this.weight += weight;
    }

    public PlantsSpecies getPlantType() {
        return plantType;
    }
}
