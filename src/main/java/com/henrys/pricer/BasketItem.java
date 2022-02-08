package com.henrys.pricer;

public class BasketItem {

    private final StockItem item;
    private final int quantity;

    public BasketItem(StockItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    StockItem getItem() {
        return this.item;
    }

    int getQuantity() {
        return this.quantity;
    }

    double price() {
        return this.item.getCost() * this.quantity;
    }

    public String toString() {
        return this.quantity + " " + this.item;
    }
}
