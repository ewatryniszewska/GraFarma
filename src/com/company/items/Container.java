package com.company.items;

public class Container {
    protected PlantsSpecies plantType;
    protected int units;

    public Container(PlantsSpecies plantType, int units) {
        this.plantType = plantType;
        this.units = units;
    }

    public String toString() {
        return plantType.plantName + "; " + units + " " + plantType.unit;
    }

    public int getUnits() {
        return units;
    }

    public void addWeight(int weight) {
        this.units += weight;
    }

    public PlantsSpecies getPlantType() {
        return plantType;
    }
}
