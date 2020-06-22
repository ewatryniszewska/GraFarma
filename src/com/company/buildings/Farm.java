package com.company.buildings;

import java.util.ArrayList;
import java.util.List;

import static com.company.Config.HECTARE_PRICE;

public class Farm {
    private List<Building> buildings;
    private int landArea;

    public Farm(int landArea) {
        buildings = new ArrayList<>();
        this.landArea = landArea;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public List<BreedingBuilding> getBreedingBuildings() {
        List<BreedingBuilding> breedingBuildings = new ArrayList<>();
        for (Building building : buildings) {
            if (building instanceof BreedingBuilding) {
                breedingBuildings.add((BreedingBuilding) building);
            }
        }
        return breedingBuildings;
    }

    public List<Building> getWarehouses() {
        List<Building> warehouses = new ArrayList<>();
        for (Building building : buildings) {
            if (building instanceof Warehouse) {
                warehouses.add(building);
            }
        }
        return warehouses;
    }

    public void addBuilding(Building building) {
        buildings.add(building);
    }

    public void removeBuilding(int index) {
        buildings.remove(index);
    }

    public int getLandArea() {
        return landArea;
    }

    public void addLandArea(int addLand) {
        landArea += addLand;
    }

    public void subtractLandArea(int subtractLand) {
        landArea -= subtractLand;
    }

    public String toString() {
        StringBuilder farmStr = new StringBuilder();
        farmStr.append("Całkowita wartość farmy: ").append(farmValue()).append(" zl \n");
        for (Building building : getBuildings()) {
            farmStr.append(building).append("\n");
        }
        farmStr.append("Powierzchnia ziemi uprawnej: ").append(getLandArea()).append(" ha");
        return farmStr.toString();
    }

    public int farmValue() {
        int value = 0;
        for (Building building : buildings) {
            if (building == null) {
                value += 0;
            } else {
                value += building.getPrice();
            }
        }
        value += getLandArea() * HECTARE_PRICE;

        return value;
    }
}
