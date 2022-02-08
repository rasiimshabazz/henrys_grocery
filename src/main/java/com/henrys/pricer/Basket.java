package com.henrys.pricer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Basket {

    private final List<BasketEntry> basketEntries;
    private final LocalDate purchaseDate;

    public Basket(List<BasketEntry> newBasketEntries, LocalDate purchaseDate) {

        if (newBasketEntries == null) newBasketEntries = new ArrayList<>();

        this.basketEntries = newBasketEntries.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        this.purchaseDate = purchaseDate;
    }

    double price(List<Coupon> coupons) {

        if (coupons == null) coupons = new ArrayList<>();

        double discount = coupons.stream()
                .mapToDouble(coupon -> coupon.calculateDiscount(this.basketEntries, this.purchaseDate))
                .sum();

        double fullPrice = this.basketEntries.stream()
                .mapToDouble(BasketEntry::price)
                .sum();

        return fullPrice - discount;
    }

    public String toString() {
        return "items: " + this.basketEntries.toString() + ", purchase date: " + this.purchaseDate.toString();
    }
}
