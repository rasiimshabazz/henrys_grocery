package com.henrys.basket;

import java.time.LocalDate;
import java.util.List;

class BreadCoupon extends Coupon {

    private final LocalDate validFromDate;
    private final LocalDate validToDate;

    protected BreadCoupon(LocalDate validFromDate, LocalDate validToDate) {
        this.validFromDate = validFromDate;
        this.validToDate = validToDate;
    }

    @Override
    double calculateDiscount(List<BasketEntry> items, LocalDate purchaseDate) {

        if (isNotApplicable(purchaseDate, this.validFromDate, this.validToDate))
            return 0;

        if (isBuyingBread(items) && isBuyingAtLeastTwoSoups(items))
            return StockItem.BREAD.getCost() / 2;

        return 0;
    }

    private boolean isBuyingAtLeastTwoSoups(List<BasketEntry> basketEntries) {
        return basketEntries.stream()
                .filter(item -> item.getItem().equals(StockItem.SOUP))
                .findFirst()
                .orElse(new BasketEntry(StockItem.SOUP, 1))
                .getQuantity() >= 2;
    }

    private boolean isBuyingBread(List<BasketEntry> basketEntries) {
        return basketEntries.stream().anyMatch(item -> item.getItem().equals(StockItem.BREAD));
    }

}
