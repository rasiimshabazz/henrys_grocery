package com.henrys.basket;

public class BasketEntry {

    private final StockItem item;
    private final int quantity;

    public BasketEntry(StockItem item, int quantity) {
        this.item = item;
        this.quantity = Math.max(quantity, 0);
    }

    public StockItem getItem() {
        return this.item;
    }

    public int getQuantity() {
        return this.quantity;
    }

    double price() {
        return this.item.getCost() * this.quantity;
    }

    public String toString() {
        return this.quantity + " " + this.item;
    }

    public boolean sameAs(StockItem stockItem) {
        return this.item.equals(stockItem);
    }
}
