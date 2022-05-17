package com.henrys.basket;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Price {

    private double decimal;

    public Price(double decimal) {
        this.decimal = decimal;
    }

    public BigDecimal value() {
        return BigDecimal.valueOf(this.decimal).setScale(2, RoundingMode.HALF_UP);
    }
}
