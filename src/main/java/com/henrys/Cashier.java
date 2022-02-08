package com.henrys;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

class Cashier {
    
    BigDecimal priceBasket(Basket basket, List<Coupon> coupons) {

        if (basket == null) return format(0);
        if (coupons == null) coupons = new ArrayList<>();

        double price = basket.price(coupons);

        return format(price);
    }

    public static BigDecimal format(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
    }

}
