package com.henrys;

import com.sun.deploy.net.MessageHeader;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private List<String> basketItems;

    Basket() {
        this.basketItems = new ArrayList<>();
    }

    public boolean addItem(String item) {
        if (item == null) return false;
        return basketItems.add(item);
    }
}
