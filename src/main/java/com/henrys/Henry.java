package com.henrys;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

class Henry {

    BigDecimal priceBasket(Basket basket) {

        if (basket.isEmpty()) return BigDecimal.ZERO;

        List<BasketItem> basketItems = basket.getItems();

        double price = basketItems.stream().mapToDouble(item -> item.getItem().getCost()).sum();

        return BigDecimal.valueOf(price);

    }
}
