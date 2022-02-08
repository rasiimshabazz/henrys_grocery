package com.henrys.pricer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum StockItem {

    SOUP(0.65), BREAD(0.80), MILK(1.30), APPLES(0.10);

    private final double cost;

    StockItem(double cost) {
        this.cost = cost;
    }

    double getCost() {
        return cost;
    }

    public static List<String> names() {
        return Arrays.asList(StockItem.values()).stream()
                .map(value -> value.name()).collect(Collectors.toList());
    }

    public static String namesToString() {
        return names().toString().toLowerCase();
    }

}
