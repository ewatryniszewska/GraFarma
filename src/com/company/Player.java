package com.company;

import com.company.buildings.Farm;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;

    private int cash;
    private List<Farm> farmList;

    private boolean gameIsOn;

    public Player(String name, int cash) {
        this.name = name;
        gameIsOn = true;
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

    public boolean getGameIsOn() {
        return gameIsOn;
    }

    public String getName() {
        return name;
    }

    public void endGame() {
        gameIsOn = false;
    }

    public void setFarmList(List<Farm> farmList) {
        this.farmList = farmList;
    }
}
