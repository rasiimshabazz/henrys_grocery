package com.henrys;

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
    public double calculateDiscount(List<BasketItem> items, LocalDate purchaseDate) {

        if (Coupon.isNotApplicable(purchaseDate, this.validFromDate, this.validToDate))
            return 0;

        if (isBuyingBread(items) && isBuyingAtLeastTwoSoups(items))
            return StockItem.BREAD.getCost() / 2;

        return 0;
    }

    private boolean isBuyingAtLeastTwoSoups(List<BasketItem> basketItems) {
        return basketItems.stream()
                .filter(item -> item.getItem().equals(StockItem.SOUP))
                .findFirst()
                .orElse(new BasketItem(StockItem.SOUP, 1))
                .getQuantity() >= 2;
    }

    private boolean isBuyingBread(List<BasketItem> basketItems) {
        return basketItems.stream().anyMatch(item -> item.getItem().equals(StockItem.BREAD));
    }

}
