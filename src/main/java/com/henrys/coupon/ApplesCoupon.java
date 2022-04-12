package com.henrys.coupon;

import com.henrys.basket.BasketEntry;
import com.henrys.basket.StockItem;

import java.time.LocalDate;
import java.util.List;

class ApplesCoupon extends Coupon {

    private static final double DISCOUNT_FACTOR = .01;

    ApplesCoupon(LocalDate validFromDate, LocalDate validToDate) {
        super(validFromDate, validToDate);
    }

    @Override
    public double calculateDiscount(List<BasketEntry> items, LocalDate purchaseDate) {
        if (!isApplicable(purchaseDate)) {
            return 0;
        }
        return items.stream().filter(item ->
                item.getItem().equals(StockItem.APPLES)
        ).findFirst().orElse(new BasketEntry(StockItem.APPLES, 0)).getQuantity() * DISCOUNT_FACTOR;
    }

    boolean isApplicable(LocalDate purchaseDate) {
        if (purchaseDate == null || this.validFromDate == null || this.validToDate == null) {
            return false;
        }
        return !purchaseDate.isBefore(this.validFromDate) && !purchaseDate.isAfter(this.validToDate);
    }

}
