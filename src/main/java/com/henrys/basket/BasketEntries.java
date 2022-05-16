package com.henrys.basket;

import java.util.List;

public class BasketEntries {

    private final List<BasketEntry> basketEntries;

    public BasketEntries(List<BasketEntry> basketEntries) {
        this.basketEntries = basketEntries;
    }

    public List<BasketEntry> getEntries() {
        return this.basketEntries;
    }
}
