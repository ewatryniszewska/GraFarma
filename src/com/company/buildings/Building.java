package com.company.buildings;

import com.company.items.Species;

public abstract class Building {
    protected String name;
    protected int price;

    public int getPrice() {
        return price;
    }

    public abstract int getLeftSpace();

    public abstract void addItems(Species species, int numberOfItems) throws Exception;

    public abstract String toString();

    public abstract String detailsToString();
}
