package org.edraak.edraaktest.utils;

public class NumbersComparatorMaager {

    public static int compare(long value1, long value2) {
        if (value1 > value2)
            return -1;

        if (value1 < value2)
            return 1;

        else
            return 0;
    }
}
