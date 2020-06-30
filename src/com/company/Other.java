package com.company;

import com.company.items.PlantsSpecies;

import static com.company.Config.YEAR_LEN;

public class Other {
    public static int weekOfTheYear(int week) {
        return (week - 1) % YEAR_LEN + 1;
    }

    public static int theYear(int week) {
        return (week - 1) / YEAR_LEN + 2020;
    }

    public static PlantsSpecies getGrowType(PlantsSpecies species) {
        for (PlantsSpecies ps : PlantsSpecies.values()) {
            if (species.growPlant.equals(ps.plantName)) {
                return ps;
            }
        }
        return null;
    }

    public static PlantsSpecies getCropType(PlantsSpecies species) {
        for (PlantsSpecies ps : PlantsSpecies.values()) {
            if (species.cropPlant.equals(ps.plantName)) {
                return ps;
            }
        }
        return null;
    }
}
