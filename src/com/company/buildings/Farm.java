package com.company.buildings;

import java.util.ArrayList;
import java.util.List;

public class Farm {
    public List<HouseForAnimal> buildings;
    public Integer price;

    public Farm() {
        this.price = 100;
        this.buildings = new ArrayList<>();
    }

    public void addBuildings(HouseForAnimal building) {
        buildings.add(building);
    }

    public String toString() {
        return "Budynki: " + buildings;
    }

    /* */
    public Integer priceFarm() {
        for (HouseForAnimal building : buildings) {
            if (building == null) {
                price += 0;
            } else {
                price += building.price;
            }
        }
//        for (HouseForAnimal building : buildings) {
//            if (building.price > 0) {
//                price += building.price;
//            }
//            return price;
//        }
        return price;
    }
}
