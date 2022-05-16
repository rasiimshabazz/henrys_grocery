package com.henrys.basket;

import com.henrys.coupon.Coupon;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Basket {

    private final List<BasketEntry> basketEntries;
    private final LocalDate purchaseDate;

    public Basket(BasketEntries basketEntries, LocalDate purchaseDate) {
        if (basketEntries == null) basketEntries = new BasketEntries(Collections.emptyList());
        this.basketEntries = basketEntries.getEntries();
        this.purchaseDate = purchaseDate;
    }

    public BigDecimal calculatePrice(List<Coupon> coupons) {
        if (coupons == null) coupons = new ArrayList<>();
        double price = fullPrice() - discount(coupons);
        return convertToDecimal(price);
    }

    private double fullPrice() {
        return this.basketEntries.stream()
                .mapToDouble(BasketEntry::price)
                .sum();
    }

    private double discount(List<Coupon> coupons) {
        return coupons.stream()
                .mapToDouble(coupon -> coupon.calculateDiscount(this.basketEntries, this.purchaseDate))
                .sum();
    }

    public String toString() {
        return "items: " + this.basketEntries.toString() + ", purchase date: " + this.purchaseDate.toString();
    }

    private static BigDecimal convertToDecimal(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
    }

}
