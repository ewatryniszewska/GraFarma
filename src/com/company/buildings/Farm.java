package com.company.buildings;

import com.company.items.Field;
import com.company.items.PlantsSpecies;

import java.util.ArrayList;
import java.util.List;

import static com.company.Config.HECTARE_PRICE;

public class Farm {
    private List<Building> buildings;
    private List<Field> fields;
    private int landArea;

    public Farm(int landArea) {
        buildings = new ArrayList<>();
        fields = new ArrayList<>();
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

    public List<Warehouse> getWarehouses() {
        List<Warehouse> warehouses = new ArrayList<>();
        for (Building building : buildings) {
            if (building instanceof Warehouse) {
                warehouses.add((Warehouse) building);
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

    public List<Field> getFields() {
        return fields;
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

    public int getFreeLandArea() {
        int currentOccupation = 0;
        for (Field field : fields) {
            currentOccupation += field.getNumberOfHectares();
        }
        return landArea - currentOccupation;
    }

    public void sow(PlantsSpecies plantType, int numberOfHectares) throws Exception {
        if (numberOfHectares > landArea) {
            throw new Exception("Chcesz za duzo zasadzic");
        }
        fields.add(new Field(plantType, numberOfHectares));
    }

    public void crop(Field fieldToCrop) {
        fieldToCrop.crop();
        if (fieldToCrop.getNumberOfCrops() == 0) {
            fields.remove(fieldToCrop);
        }
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

    public String toString() {
        StringBuilder farmStr = new StringBuilder();
        farmStr.append("Całkowita wartość farmy: ").append(farmValue()).append(" zl \n");
        for (Building building : getBuildings()) {
            farmStr.append(building).append("\n");
        }
        farmStr.append("Powierzchnia ziemi uprawnej: ").append(getLandArea()).append(" ha");
        return farmStr.toString();
    }
}
