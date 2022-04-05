package com.henrys.coupon;

import com.henrys.basket.BasketEntry;
import com.henrys.basket.StockItem;

import java.time.LocalDate;
import java.util.List;

class BreadCoupon extends Coupon {

    private static final double DISCOUNT_FACTOR = 0.5;
    public static final int DISCOUNT_SOUP_QUANTITY = 2;

    BreadCoupon(LocalDate validFromDate, LocalDate validToDate) {
        super(validFromDate, validToDate);
    }

    @Override
    public double calculateDiscount(List<BasketEntry> items, LocalDate purchaseDate) {
        if (!isApplicable(purchaseDate)) {
            return 0;
        }
        if (isBuyingBread(items) && isBuyingAtLeastTwoSoups(items)) {
            return StockItem.BREAD.getCost() * DISCOUNT_FACTOR;
        }
        return 0;
    }

    private boolean isBuyingAtLeastTwoSoups(List<BasketEntry> basketEntries) {
        return basketEntries.stream()
                .filter(item -> StockItem.SOUP.equals(item.getItem()))
                .findFirst().orElse(new BasketEntry(StockItem.SOUP, 0))
                .getQuantity() >= DISCOUNT_SOUP_QUANTITY;
    }

    private boolean isBuyingBread(List<BasketEntry> basketEntries) {
        return basketEntries.stream().anyMatch(item -> item.getItem().equals(StockItem.BREAD));
    }
}
