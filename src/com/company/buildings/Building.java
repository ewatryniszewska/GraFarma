package com.company.buildings;

public abstract class Building implements Cloneable {
    protected String name;
    protected int price;

    public int getPrice() {
        return price;
    }

    public abstract String toString();
}
