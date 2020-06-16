package com.company.buildings;

public abstract class Building {
    protected String name;
    protected int price;

    public int getPrice() {
        return price;
    }

    public abstract String toString();
}
