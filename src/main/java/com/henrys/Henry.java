package com.henrys;

import java.math.BigDecimal;

class Henry {

    BigDecimal priceBasket(Basket basket) {

        if (basket.isEmpty()) return BigDecimal.ZERO;

        double price = basket.price();

        return BigDecimal.valueOf(price);
    }

}
