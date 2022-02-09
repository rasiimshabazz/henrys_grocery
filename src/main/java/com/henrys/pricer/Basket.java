package com.henrys.pricer;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Basket {

    private final List<BasketEntry> basketEntries;
    private final LocalDate purchaseDate;

    public Basket(List<BasketEntry> basketEntries, LocalDate purchaseDate) {

        if (basketEntries == null) basketEntries = new ArrayList<>();

        this.basketEntries = mergeQuantities(filterOutNulls(basketEntries));
        this.purchaseDate = purchaseDate;
    }

    double calculatePrice(List<Coupon> coupons) {

        if (coupons == null) coupons = new ArrayList<>();

        return fullPrice() - discount(coupons);
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

    private List<BasketEntry> mergeQuantities(List<BasketEntry> unmerged) {
        List<StockItem> distinctItems = unmerged.stream()
                .map(BasketEntry::getItem)
                .distinct().collect(Collectors.toList());
        return distinctItems.stream()
                .map(distinctItem -> mergeQuantitiesByStockItem(unmerged, distinctItem))
                .collect(Collectors.toList());
    }

    private BasketEntry mergeQuantitiesByStockItem(List<BasketEntry> repeatedEntries, StockItem distinctItem) {
        int count = repeatedEntries.stream()
                .filter(repeat -> repeat.getItem().equals(distinctItem))
                .mapToInt(BasketEntry::getQuantity).sum();
        return new BasketEntry(distinctItem, count);
    }

    private List<BasketEntry> filterOutNulls(List<BasketEntry> newBasketEntries) {
        return newBasketEntries.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public String toString() {
        return "items: " + this.basketEntries.toString() + ", purchase date: " + this.purchaseDate.toString();
    }
}
