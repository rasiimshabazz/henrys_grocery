package com.henrys;

import java.util.ArrayList;
import java.util.List;

class Basket {

    private List<StockItem> basketItems;

    Basket() {
        this.basketItems = new ArrayList<>();
    }

    boolean addItem(StockItem item) {
        if (item == null) return false;
        return basketItems.add(item);
    }

    public boolean isEmpty() {
        return basketItems.isEmpty();
    }

    public List<StockItem> getItems() {
        return basketItems;
    }
}
