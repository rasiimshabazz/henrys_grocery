package com.henrys;

class BasketItem {

    private final StockItem item;
    private final int quantity;

    BasketItem(StockItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public StockItem getItem() {
        return this.item;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public double price() {
        return this.item.getCost() * this.quantity;
    }
}
