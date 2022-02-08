package com.henrys;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

class Basket {

    private List<BasketItem> basketItems;
    private LocalDate purchaseDate;

    public Basket(List<BasketItem> newBasketItems, LocalDate purchaseDate) {
        this.basketItems = newBasketItems.stream()
                .filter(item -> item != null).collect(Collectors.toList());

        this.purchaseDate = purchaseDate;
    }

    double price(List<Coupon> coupons) {

        double discount = coupons.stream()
                .mapToDouble(coupon -> coupon.calculateDiscount(this.basketItems, this.purchaseDate))
                .sum();

        double fullPrice = this.basketItems.stream()
                .mapToDouble(item -> item.price())
                .sum();

        return fullPrice - discount;
    }

}
