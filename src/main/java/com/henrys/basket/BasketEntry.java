package com.henrys.basket;

public class BasketEntry {

    public final StockItem item;
    public final int quantity;

    public BasketEntry(StockItem item, int quantity) {
        this.item = item;
        this.quantity = Math.max(quantity, 0);
    }

    public String toString() {
        return this.quantity + " " + this.item;
    }
}
