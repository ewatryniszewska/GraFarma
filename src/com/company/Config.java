package com.company;

import com.company.buildings.BreedingBuilding;
import com.company.buildings.Building;
import com.company.buildings.Warehouse;

public class Config {
    public static final int NUMBER_OF_FARMS_TO_OFFER = 3;
    public static final int HECTARE_PRICE = 30000;
    public static final double VALUE_LOSS = 0.9;
    public static final Building[] BUILDINGS = {
            new BreedingBuilding(50000, 100),
            new BreedingBuilding(100000, 200),
            new BreedingBuilding(200000, 300),
            new BreedingBuilding(500000, 500),
            new BreedingBuilding(1000000, 1000),
            new Warehouse(250000, 100000),
            new Warehouse(500000, 250000),
            new Warehouse(750000, 500000),
            new Warehouse(1500000, 1000000),
            new Warehouse(3000000, 5000000)
    };
//    public static final Warehouse[] WAREHOUSES = {
//
//    }
}
