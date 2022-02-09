package com.henrys.basket;

import java.time.LocalDate;
import java.util.List;

class BreadCoupon extends Coupon {

    private static final double DISCOUNT_FACTOR = 0.5;
    public static final int DISCOUNT_AMOUNT_SOUP_NEEDED = 2;

    protected BreadCoupon(LocalDate validFromDate, LocalDate validToDate) {
        super(validFromDate, validToDate);
    }

    @Override
    double calculateDiscount(List<BasketEntry> items, LocalDate purchaseDate) {

        if (!isApplicable(purchaseDate, this.validFromDate, this.validToDate)) {

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
                .getQuantity() >= DISCOUNT_AMOUNT_SOUP_NEEDED;
    }

    private boolean isBuyingBread(List<BasketEntry> basketEntries) {
        return basketEntries.stream().anyMatch(item -> item.getItem().equals(StockItem.BREAD));
    }
}
