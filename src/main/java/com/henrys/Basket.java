package com.henrys;

import java.util.List;
import java.util.stream.Collectors;

class Basket {

    private List<BasketItem> basketItems;

    public Basket(List<BasketItem> newBasketItems) {

        this.basketItems = newBasketItems.stream()
                .filter(item -> item != null).collect(Collectors.toList());
    }

    double price(List<Coupon> coupons) {

        double discount = coupons.stream().mapToDouble(coupon -> coupon.calculateDiscount(this.basketItems)).sum();

        double fullPrice = this.basketItems.stream().mapToDouble(item -> item.price()).sum();

        return fullPrice - discount;

    }

}
