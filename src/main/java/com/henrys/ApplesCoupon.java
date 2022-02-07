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
    public double calculateDiscount(List<BasketItem> basketItems, LocalDate purchaseDate) {

        if (!isValid(purchaseDate)) return 0;

        boolean isPurchasedWithinValidPeriod = true;
        if (!isPurchasedWithinValidPeriod) return 0;

        int appleCount = basketItems.stream().filter(item ->
            item.getItem().equals(StockItem.APPLES)
        ).findFirst().orElse(new BasketItem(StockItem.APPLES, 0)).getQuantity();

        return appleCount * .01;
    }

    public boolean isValid(LocalDate purchaseDate) {

        if (this.validFromDate == null || this.validToDate == null) return true;

        return purchaseDate.isAfter(this.validFromDate) && purchaseDate.isBefore(this.validToDate);
    }
}
