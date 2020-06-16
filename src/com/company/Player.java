package com.company;

import com.company.buildings.Farm;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int cash;
    private List<Farm> farmList;

    public Player(int cash) {
        this.cash = cash;
        farmList = new ArrayList<>();
    }

    public int getCash() {
        return cash;
    }

    public void subtractCash(int cash) {
        this.cash -= cash;
    }

    public void addCash(int cash) {
        this.cash += cash;
    }

    public List<Farm> getFarmList() {
        return farmList;
    }

    public void setFarmList(List<Farm> farmList) {
        this.farmList = farmList;
    }
}
