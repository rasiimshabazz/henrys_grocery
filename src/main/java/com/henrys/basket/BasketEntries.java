package com.henrys.basket;

import java.util.ArrayList;
import java.util.List;

public class BasketEntries {

    private final List<BasketEntry> basketEntries;

    public BasketEntries(List<BasketEntry> basketEntries) {
        this.basketEntries = basketEntries;
    }

    static List<BasketEntry> validateBasketEntries(List<BasketEntry> basketEntries) {
        if (basketEntries == null) return new ArrayList<>();
        return BasketEntry.mergeBasketEntries(basketEntries);
    }

    public List<BasketEntry> getEntries() {
        return this.basketEntries;
    }
}
