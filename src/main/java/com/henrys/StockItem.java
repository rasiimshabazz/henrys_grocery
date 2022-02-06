package com.henrys;

import java.math.BigDecimal;

enum StockItem {

    SOUP(0.65), BREAD(0.80);

    private double cost;

    StockItem(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }
}
