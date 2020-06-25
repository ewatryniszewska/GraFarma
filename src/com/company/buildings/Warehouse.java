package com.company.buildings;

import com.company.items.Container;
import com.company.items.PlantsSpecies;
import com.company.items.Species;

import java.util.ArrayList;
import java.util.List;

public class Warehouse extends Building {
    private int maxFodderWeight;
    private List<Container> containers;

    public Warehouse(int price, int maxCapacity) {
        name = "Magazyn";
        this.price = price;
        this.maxFodderWeight = maxCapacity;
        containers = new ArrayList<>();
    }

    public int getMaxFodderWeight() {
        return maxFodderWeight;
    }

    public int getOccupiedSpace() {
        int currentWeight = 0;
        for (Container container : containers) {
            currentWeight += container.getWeight();
        }
        return currentWeight;
    }

    public List<Container> getContainers() {
        return containers;
    }

    public void subtractPlants(Container container, int amount) throws Exception {
        if (container.getWeight() < amount) {
            throw new Exception("Nie mozna usunac az tyle zasobu");
        }
        container.addWeight(-amount);
        if (container.getWeight() == 0) {
            containers.remove(container);
        }
    }

    @Override
    public int getLeftSpace() {
        return maxFodderWeight - getOccupiedSpace();
    }

    @Override
    public void addItems(Species species, int numberOfItems) throws Exception {
        if (!(species instanceof PlantsSpecies)) {
            throw new Exception("Ten budynek moze przechowywac jedynie kontenery na rosliny!");
        }

        for (Container container : containers) {
            if (container.getPlantType() == species) {
                container.addWeight(numberOfItems);
                return;
            }
        }

        containers.add(new Container((PlantsSpecies) species, numberOfItems));
    }

    @Override
    public String toString() {
        return name + ";\t\t\t" + price + " zl;\t" + maxFodderWeight + " max kg paszy";
    }

    @Override
    public String detailsToString() {
        String str = toString();
        str += "\nObecnie w budynku znajduje sie " + containers.size() + " roznych gatunkow roslin w kontenerach.\n";
        str += "Ich waga to " + getOccupiedSpace() + " kg. Dostepne miejsce dla " + getLeftSpace() + " kg roslin\n";
        return str;
    }
}
