package com.henrys;

import java.time.LocalDate;
import java.util.List;

class ApplesCoupon implements Coupon {

    private LocalDate validFromDate;
    private LocalDate validToDate;

    ApplesCoupon() {
    }

    public ApplesCoupon(LocalDate validFromDate, LocalDate validToDate) {
        this.validFromDate = validFromDate;
        this.validToDate = validToDate;
    }

    @Override
    public double calculateDiscount(List<BasketItem> basketItems) {

        int appleCount = basketItems.stream().filter(item ->
            item.getItem().equals(StockItem.APPLES)
        ).findFirst().orElse(new BasketItem(StockItem.APPLES, 0)).getQuantity();

        return appleCount * .01;
    }
}
