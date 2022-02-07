package com.henrys;

import java.util.List;

class BreadCoupon implements Coupon {

    @Override
    public double calculateDiscount(List<BasketItem> basketItems) {

        boolean isBuyingBread = basketItems.stream().anyMatch(item -> {
            return item.getItem().equals(StockItem.BREAD);
        });

        boolean isBuyingAtLeastTwoSoups = basketItems.stream().filter(item -> {
            return item.getItem().equals(StockItem.SOUP);
        }).findFirst().orElse(new BasketItem(StockItem.SOUP, 1)).getQuantity() >= 2;

        if (isBuyingBread && isBuyingAtLeastTwoSoups)
            return StockItem.BREAD.getCost() / 2;

        return 0;
    }
}
