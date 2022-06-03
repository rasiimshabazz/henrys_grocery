package com.henrys.coupon;

import com.henrys.basket.BasketEntries;
import com.henrys.basket.StockItem;
import java.time.LocalDate;

class BreadCoupon extends Coupon {

    private static final double DISCOUNT_FACTOR = 0.5;
    public static final int DISCOUNT_SOUP_QUANTITY = 2;

    BreadCoupon(LocalDate validFromDate, LocalDate validToDate) {
        super(validFromDate, validToDate);
    }

    @Override
    public double calculateDiscount(BasketEntries basketEntries, LocalDate purchaseDate) {
        if (!isApplicable(purchaseDate)) return 0;
        if (basketEntries.countStockItem(StockItem.BREAD) == 0) return 0;
        if (basketEntries.countStockItem(StockItem.SOUP) < DISCOUNT_SOUP_QUANTITY) return 0;
        return StockItem.BREAD.getCost() * DISCOUNT_FACTOR;
    }
}
