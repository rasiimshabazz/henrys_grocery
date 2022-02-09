package com.henrys.pricer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Pricer {

    public static BigDecimal priceBasket(Basket basket, List<Coupon> coupons) {

        if (basket == null) return format(0);
        if (coupons == null) coupons = new ArrayList<>();

        double price = basket.calculatePrice(coupons);

        return format(price);
    }

    static BigDecimal format(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
    }

}
