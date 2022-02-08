package com.henrys;

import java.time.LocalDate;
import java.util.List;

class ApplesCoupon extends Coupon {

    private LocalDate validFromDate;
    private LocalDate validToDate;

    protected ApplesCoupon(LocalDate validFromDate, LocalDate validToDate) {
        this.validFromDate = validFromDate;
        this.validToDate = validToDate;
    }

    @Override
    public double calculateDiscount(List<BasketItem> items, LocalDate purchaseDate) {

        if (!Coupon.isValid(purchaseDate, this.validFromDate, this.validToDate))
            return 0;

        return numberOfApples(items) * .01;
    }

    private int numberOfApples(List<BasketItem> basketItems) {
        int appleCount = basketItems.stream().filter(item ->
            item.getItem().equals(StockItem.APPLES)
        ).findFirst().orElse(new BasketItem(StockItem.APPLES, 0)).getQuantity();
        return appleCount;
    }

}
