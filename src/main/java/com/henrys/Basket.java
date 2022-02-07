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

        double discount = 0;

        if (discountsOn) {

            discount = new BreadCoupon().discount(this.basketItems);
        }

        return this.basketItems.stream().mapToDouble(item -> item.price()).sum() - discount;
    }

}
