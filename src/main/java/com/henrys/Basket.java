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

            boolean hasBread = basketItems.stream().anyMatch(item -> {
                return item.getItem().equals(StockItem.BREAD);
            });

            boolean hasTwoSoups = basketItems.stream().filter(item -> {
                return item.getItem().equals(StockItem.SOUP);
            }).findFirst().orElse(new BasketItem(StockItem.SOUP, 1)).getQuantity() >= 2;


            if (hasBread && hasTwoSoups) {
                discount = StockItem.BREAD.getCost() / 2;
            }
        }

        return this.basketItems.stream().mapToDouble(item -> item.price()).sum() - discount;
    }

}
