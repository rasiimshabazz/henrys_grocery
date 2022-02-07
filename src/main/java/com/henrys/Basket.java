package com.henrys;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Basket {

    private List<BasketItem> basketItems;

    public Basket(List<BasketItem> basketItems) {
        this.basketItems = basketItems.stream()
                .filter(item -> item != null).collect(Collectors.toList());
    }

    @Deprecated
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

    public List<BasketItem> getItems() {
        return basketItems;
    }

}
