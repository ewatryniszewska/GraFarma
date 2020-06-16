package com.company.buildings;

import java.util.ArrayList;
import java.util.List;

public class Farm {
    private List<BreedingBuilding> buildings;

    public Farm() {
        this.buildings = new ArrayList<>();
    }

    public List<BreedingBuilding> getBuildings() {
        return buildings;
    }

    public void addBuilding(BreedingBuilding building) {
        buildings.add(building);
    }

    public void removeBuilding(int index) {
        buildings.remove(index);
    }

    public String toString() {
        return "Budynki: " + buildings;
    }

    public int priceFarm() {
        int price = 10000;

        for (BreedingBuilding building : buildings) {
            if (building == null) {
                price += 0;
            } else {
                price += building.getPrice();
            }
        }

        return price;
    }
}
