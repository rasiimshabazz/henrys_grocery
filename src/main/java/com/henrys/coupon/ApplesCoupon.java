package com.henrys.coupon;

import com.henrys.basket.BasketEntry;
import com.henrys.basket.StockItem;

import java.time.LocalDate;
import java.util.List;

public class ApplesCoupon extends Coupon {

    private static final double APPLE_DISCOUNT_FACTOR = .01;

    public ApplesCoupon(LocalDate validFromDate, LocalDate validToDate) {
        super(validFromDate, validToDate, "");
    }

    @Override
    public double calculateDiscount(List<BasketEntry> items, LocalDate purchaseDate) {
        if (purchaseDate == null || this.validFromDate == null || this.validToDate == null) {
            return 0;
        }
        if (purchaseDate.isBefore(this.validFromDate) || purchaseDate.isAfter(this.validToDate)) {
            return 0;
        }
        return items.stream().filter(item ->
                item.getItem().equals(StockItem.APPLES)
        ).findFirst().orElse(new BasketEntry(StockItem.APPLES, 0)).getQuantity() * APPLE_DISCOUNT_FACTOR;
    }

}
