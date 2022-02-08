package com.henrys;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class Basket {

    private final List<BasketItem> basketItems;
    private final LocalDate purchaseDate;

    public Basket(List<BasketItem> newBasketItems, LocalDate purchaseDate) {

        if (newBasketItems == null) newBasketItems = new ArrayList<>();

        this.basketItems = newBasketItems.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        this.purchaseDate = purchaseDate;
    }

    double price(List<Coupon> coupons) {

        if (coupons == null) coupons = new ArrayList<>();

        double discount = coupons.stream()
                .mapToDouble(coupon -> coupon.calculateDiscount(this.basketItems, this.purchaseDate))
                .sum();

        double fullPrice = this.basketItems.stream()
                .mapToDouble(BasketItem::price)
                .sum();

        return fullPrice - discount;
    }

}
