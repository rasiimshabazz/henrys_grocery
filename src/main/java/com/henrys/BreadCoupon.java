package com.henrys;

import java.util.List;

public class BreadCoupon {

    public double discount(List<BasketItem> basketItems) {

        double discount = 0;

        boolean hasBread = basketItems.stream().anyMatch(item -> {
            return item.getItem().equals(StockItem.BREAD);
        });

        boolean hasTwoSoups = basketItems.stream().filter(item -> {
            return item.getItem().equals(StockItem.SOUP);
        }).findFirst().orElse(new BasketItem(StockItem.SOUP, 1)).getQuantity() >= 2;


        if (hasBread && hasTwoSoups) {
            discount = StockItem.BREAD.getCost() / 2;
        }
        return discount;
    }
}
