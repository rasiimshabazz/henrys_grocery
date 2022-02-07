package com.henrys;

enum StockItem {

    SOUP(0.65), BREAD(0.80), MILK(1.30), APPLES(0.10);

    private double cost;

    StockItem(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }
}
