package com.henrys.coupon;

import com.henrys.basket.BasketEntries;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Coupons {

    public final List<Coupon> coupons;

    public Coupons(List<Coupon> coupons) {
        if (coupons == null) coupons = new ArrayList<>();
        this.coupons = coupons;
    }

    public double discount(BasketEntries basketEntries, LocalDate purchaseDate) {
        return this.coupons.stream()
                .mapToDouble(coupon -> coupon.calculateDiscount(basketEntries, purchaseDate))
                .sum();
    }
}
