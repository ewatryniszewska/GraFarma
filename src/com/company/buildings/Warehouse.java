package com.company.buildings;

public class Warehouse extends Building {
    private int maxFodderWeight;

    public Warehouse(int price, int maxCapacity) {
        this.name = "Magazyn";
        this.price = price;
        this.maxFodderWeight = maxCapacity;
    }

    @Override
    public String toString() {
        return name + ";\t" + price + " zl;\t" + maxFodderWeight + " max kg paszy";
    }
}
