package com.company.buildings;

import java.util.ArrayList;
import java.util.List;

public class Farm {
    private List<Building> buildings;

    public Farm() {
        this.buildings = new ArrayList<>();
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void addBuilding(Building building) {
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

        for (Building building : buildings) {
            if (building == null) {
                price += 0;
            } else {
                price += building.getPrice();
            }
        }

        return price;
    }
}
