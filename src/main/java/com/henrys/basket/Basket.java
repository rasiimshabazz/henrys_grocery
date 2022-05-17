package com.henrys.basket;

import com.henrys.coupon.Coupon;
import com.henrys.coupon.Coupons;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Basket {
    private final BasketEntries basketEntries;
    private final LocalDate purchaseDate;
    public Basket(BasketEntries basketEntries, LocalDate purchaseDate) {
        if (basketEntries == null) basketEntries = new BasketEntries(Collections.emptyList());
        this.basketEntries = basketEntries;
        this.purchaseDate = purchaseDate;
    }
    public BigDecimal calculatePrice(List<Coupon> coupons) {
        if (coupons == null) coupons = new ArrayList<>();
        return priceBasket(new Coupons(coupons));
    }

    private BigDecimal priceBasket(Coupons coupons) {
        if (coupons == null) coupons = new Coupons(new ArrayList<>());
        double price = this.basketEntries.fullPrice() - discount(coupons.coupons);
        return BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP);
    }

    private double discount(List<Coupon> coupons) {
        return coupons.stream()
                .mapToDouble(coupon -> coupon.calculateDiscount(this.basketEntries, this.purchaseDate))
                .sum();
    }
    public String toString() {
        return "items: " + this.basketEntries.toString() + ", purchase date: " + this.purchaseDate.toString();
    }
}
