package com.henrys;

import java.util.List;

class Henry {

    double priceBasket(Basket basket) {

        double price = 0;

        if (basket.isEmpty()) return 0;

        List<StockItem> basketItems = basket.getItems();

        if (basketItems.get(0).equals(StockItem.SOUP))
            return 0.65;

        return price;
    }
}
