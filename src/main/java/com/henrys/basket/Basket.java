package com.henrys.basket;

import com.henrys.coupon.Coupon;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Basket {

    private final List<BasketEntry> basketEntries;
    private final LocalDate purchaseDate;
    private List<Coupon> coupons;

    public Basket(List<BasketEntry> basketEntries, LocalDate purchaseDate, List<Coupon> coupons) {

        if (basketEntries == null) basketEntries = new ArrayList<>();

        List<BasketEntry> unmerged = basketEntries.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        List<StockItem> distinctItems = unmerged.stream()
                .map(BasketEntry::getItem)
                .distinct().collect(Collectors.toList());

        this.basketEntries = distinctItems.stream()
                .map(distinctItem -> {
                    int count = unmerged.stream()
                            .filter(repeat -> repeat.getItem().equals(distinctItem))
                            .mapToInt(BasketEntry::getQuantity).sum();
                    return new BasketEntry(distinctItem, count);
                })
                .collect(Collectors.toList());

        this.purchaseDate = purchaseDate;
        this.coupons = coupons;
    }

    public BigDecimal calculatePrice(List<Coupon> coupons) {
        if (coupons == null) coupons = new ArrayList<>();

        double price = this.basketEntries.stream()
                .mapToDouble(BasketEntry::price)
                .sum() - coupons.stream()
                .mapToDouble(coupon -> coupon.calculateDiscount(this.basketEntries, this.purchaseDate))
                .sum();

        return BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP);
    }

    public String toString() {
        return "items: " + this.basketEntries.toString() + ", purchase date: " + this.purchaseDate.toString();
    }

}
