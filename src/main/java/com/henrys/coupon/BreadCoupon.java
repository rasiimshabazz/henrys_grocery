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
        if (basketEntries.contains(StockItem.BREAD) && isBuyingAtLeastTwoSoups(basketEntries)) {
            return StockItem.BREAD.getCost() * DISCOUNT_FACTOR;
        }
        return 0;
    }

    private boolean isBuyingAtLeastTwoSoups(BasketEntries basketEntries) {
        return basketEntries.getEntries().stream()
                .filter(item -> StockItem.SOUP.equals(item.getItem()))
                .findFirst().orElse(new BasketEntry(StockItem.SOUP, 0))
                .getQuantity() >= DISCOUNT_SOUP_QUANTITY;
    }

}
