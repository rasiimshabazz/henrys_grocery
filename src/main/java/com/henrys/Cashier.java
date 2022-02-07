package com.henrys;

import java.math.BigDecimal;
import java.math.RoundingMode;

class Cashier {

    BigDecimal priceBasket(Basket basket, boolean discount) {

        if (basket == null) return format(0);

        return format(basket.price(discount));
    }

    private BigDecimal format(double price) {
        return BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP);
    }
}
