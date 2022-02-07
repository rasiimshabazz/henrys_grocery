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

    double price() {
        return this.basketItems.stream().mapToDouble(item -> item.price()).sum();
    }

}
