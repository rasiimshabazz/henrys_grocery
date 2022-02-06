package com.henrys;

import java.util.ArrayList;
import java.util.List;

class Basket {

    private List<StockItem> basketItems;
    private List<BasketItem> newBasketItems;

    Basket() {
        this.basketItems = new ArrayList<>();
        this.newBasketItems = new ArrayList<>();
    }

    boolean addItem(StockItem item) {
        if (item == null) return false;
        basketItems.add(item);
        return addNewBasketItem(item);
    }

    private boolean addNewBasketItem(StockItem item) {

        BasketItem basketItems = new BasketItem(item, 1);
        return newBasketItems.add(basketItems);
    }

    public boolean isEmpty() {
        return basketItems.isEmpty();
    }

    public List<StockItem> getItems() {
        return basketItems;
    }
}
