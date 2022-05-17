package com.henrys.coupon;

import com.henrys.basket.BasketEntries;
import com.henrys.basket.StockItem;

import java.time.LocalDate;
class ApplesCoupon extends Coupon {

    private static final double DISCOUNT_FACTOR = .01;

    ApplesCoupon(LocalDate validFromDate, LocalDate validToDate) {
        super(validFromDate, validToDate);
    }

    @Override
    public double calculateDiscount(BasketEntries basketEntries, LocalDate purchaseDate) {
        if (!isApplicable(purchaseDate)) {
            return 0;
        }
        return basketEntries.count(StockItem.APPLES) * DISCOUNT_FACTOR;
    }


}
