package com.henrys;

import java.util.List;

class Henry {

    double priceBasket(Basket basket) {

        double price = 0;

        if (basket.isEmpty()) return 0;

        List<String> basketItems = basket.getItems();

        if (basketItems.get(0).equalsIgnoreCase("soup"))
            return 0.65;

        return price;
    }
}
