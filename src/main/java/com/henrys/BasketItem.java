package com.henrys;

class BasketItem {

    private StockItem item;
    private int quantity;

    BasketItem(StockItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public StockItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
}
