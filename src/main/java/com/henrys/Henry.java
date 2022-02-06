package com.henrys;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

class Henry {

    BigDecimal priceBasket(Basket basket) {

        if (basket.isEmpty()) return BigDecimal.ZERO;

        double price = price(basket);

        return BigDecimal.valueOf(price);

    }

    private double price(Basket basket) {
        List<BasketItem> basketItems = basket.getItems();

        double price = basketItems.stream().mapToDouble(item -> item.price()).sum();
        return price;
    }
}
