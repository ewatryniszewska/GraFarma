package com.company;

import static com.company.Config.YEAR_LEN;

public class Other {
    public static int weekOfTheYear(int week) {
        return (week - 1) % YEAR_LEN + 1;
    }

    public static int theYear(int week) {
        return (week - 1) / YEAR_LEN + 2020;
    }
}
