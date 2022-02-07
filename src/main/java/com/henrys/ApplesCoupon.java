package com.henrys;

import java.util.List;

class ApplesCoupon implements Coupon {

    @Override
    public double calculateDiscount(List<BasketItem> basketItems) {

        int appleCount = basketItems.stream().filter(item ->
            item.getItem().equals(StockItem.APPLES)
        ).findFirst().orElse(new BasketItem(StockItem.APPLES, 0)).getQuantity();

        return appleCount * .01;
    }
}
