package com.company.buildings;

public class Warehouse extends Building {
    private int maxFodderWeight;

    public Warehouse(int price, int maxCapacity) {
        name = "Magazyn";
        this.price = price;
        this.maxFodderWeight = maxCapacity;
    }

    public int getMaxFodderWeight() {
        return maxFodderWeight;
    }

    @Override
    public String toString() {
        return name + ";\t\t\t" + price + " zl;\t" + maxFodderWeight + " max kg paszy";
    }
}
