package com.company;

import com.company.buildings.BreedingBuilding;
import com.company.buildings.Building;
import com.company.buildings.Warehouse;

public class Config {
    public static final int NUMBER_OF_FARMS_TO_OFFER = 3;
    public static final int HECTARE_PRICE = 10000;
    public static final double VALUE_LOSS = 0.9;
    public static final int YEAR_LEN = 52;

    public static final Building[] BUILDINGS = {
            new BreedingBuilding(5000, 5),
            new BreedingBuilding(6900, 7),
            new BreedingBuilding(8700, 9),
            new BreedingBuilding(9500, 10),
            new BreedingBuilding(14000, 15),
            new Warehouse(15000, 5000),
            new Warehouse(17000, 6000),
            new Warehouse(19000, 7000),
            new Warehouse(21000, 8000),
            new Warehouse(25000, 10000)
    };
}
