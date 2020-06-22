package com.company.items;

import com.company.Config.PlantsSpecies;

public class Plant {
    protected PlantsSpecies plantType;
    protected int age;
    protected int numberOfCrops;

    public String toString() {
        return plantType.plantName + "; " + age + " tygodni; pozostalo " + numberOfCrops + " zbiorow";
    }
}
