package com.henrys;

import java.util.ArrayList;
import java.util.List;

class Basket {

    private List<String> basketItems;

    Basket() {
        this.basketItems = new ArrayList<>();
    }

    boolean addItem(String item) {
        if (item == null) return false;
        return basketItems.add(item);
    }

    public boolean isEmpty() {
        return basketItems.isEmpty();
    }

    public List<String> getItems() {
        return basketItems;
    }
}
