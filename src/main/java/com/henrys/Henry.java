package com.henrys;

import java.math.BigDecimal;
import java.math.RoundingMode;

class Henry {

    BigDecimal priceBasket(Basket basket) {

        if (basket == null) return BigDecimal.ZERO;

        return format(basket.price());
    }

    private BigDecimal format(double price) {
        return BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP);
    }
}
