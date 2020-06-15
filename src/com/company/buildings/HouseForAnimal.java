package com.company.buildings;

public class HouseForAnimal {
    public String name;
    public Integer price;

    public HouseForAnimal(Integer price) {
        this.name = "Dom dla zwierzat";
        this.price = price;
    }

    public String toString() {
        return this.name + " cena: " + this.price;
    }
}
