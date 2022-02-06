package com.henrys;

import java.util.ArrayList;
import java.util.List;

class Basket {

    private List<BasketItem> basketItems;

    Basket() {
       this.basketItems = new ArrayList<>();
    }

    double price() {
        return this.basketItems.stream().mapToDouble(item -> item.price()).sum();
    }

    boolean addItem(BasketItem item) {
        if (item == null) return false;
        return basketItems.add(item);
    }

    public boolean isEmpty() {
        return basketItems.isEmpty();
    }

    public List<BasketItem> getItems() {
        return basketItems;
    }

}
