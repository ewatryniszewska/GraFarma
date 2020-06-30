package com.company.items;

public class AnimalsSummary {
    public int matureSum;
    public int immatureSum;
    public int toYoungSum;
    public int minAge;
    public int maxAge;

    public AnimalsSummary() {
        matureSum = 0;
        immatureSum = 0;
        toYoungSum = 0;
        minAge = Integer.MAX_VALUE;
        maxAge = 0;
    }

    public String toString() {
        return "Dojrzalych:                   " + matureSum +
                "\nNiedojrzalych:                " + immatureSum +
                "\nZa mlode na sprzedaz:         " + toYoungSum +
                "\nMinimalny / Maksymalny wiek:  " + minAge + "/" + maxAge;
    }
}
