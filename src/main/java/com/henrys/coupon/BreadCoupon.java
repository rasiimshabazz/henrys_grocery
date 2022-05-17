package com.henrys.coupon;

import com.henrys.basket.BasketEntries;
import com.henrys.basket.BasketEntry;
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
        if (!isApplicable(purchaseDate)) {
            return 0;
        }
        if (basketEntries.contains(StockItem.BREAD) && isBuyingAtLeastTwoSoups(basketEntries, StockItem.SOUP, DISCOUNT_SOUP_QUANTITY)) {
            return StockItem.BREAD.getCost() * DISCOUNT_FACTOR;
        }
        return 0;
    }

    private boolean isBuyingAtLeastTwoSoups(BasketEntries basketEntries, StockItem stockItem, int amount) {
        return basketEntries.getEntries().stream()
                .filter(item -> stockItem.equals(item.getItem()))
                .findFirst().orElse(new BasketEntry(stockItem, 0))
                .getQuantity() >= amount;
    }

}
