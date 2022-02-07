package com.henrys;

import java.util.List;
import java.util.stream.Collectors;

class Basket {

    private List<BasketItem> basketItems;

    public Basket(List<BasketItem> basketItems) {
        this.basketItems = basketItems.stream()
                .filter(item -> item != null).collect(Collectors.toList());
    }

    double price(boolean discountsOn) {

        BreadCoupon coupon = new BreadCoupon();
        return priceWithCoupons(discountsOn, coupon);
    }

    private double priceWithCoupons(boolean discountsOn, BreadCoupon coupon) {

        double discount = 0;

        if (discountsOn) {
            discount = coupon.discount(this.basketItems);
        }

        return this.basketItems.stream().mapToDouble(item -> item.price()).sum() - discount;
    }

}
