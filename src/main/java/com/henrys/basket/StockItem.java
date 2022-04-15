package com.henrys.basket;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum StockItem {

    SOUP(0.65, Unit.TIN), BREAD(0.80, Unit.LOAF),
    MILK(1.30, Unit.BOTTLE), APPLES(0.10, Unit.SINGLE);

    public final double cost;
    public final Unit unit;

    StockItem(double cost, Unit unit) {
        this.cost = cost;
        this.unit = unit;
    }

}
