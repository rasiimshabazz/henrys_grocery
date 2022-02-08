package com.henrys;

import java.time.LocalDate;
import java.util.List;

class BreadCoupon extends Coupon {

    private LocalDate validFromDate;
    private LocalDate validToDate;

    protected BreadCoupon(LocalDate validFromDate, LocalDate validToDate) {
        this.validFromDate = validFromDate;
        this.validToDate = validToDate;
    }

    @Override
    public double calculateDiscount(List<BasketItem> basketItems, LocalDate purchaseDate) {

        if (!Coupon.isValid(purchaseDate, this.validFromDate, this.validToDate)) return 0;

        if (isBuyingBread(basketItems) && isBuyingAtLeastTwoSoups(basketItems))
            return StockItem.BREAD.getCost() / 2;

        return 0;
    }

    private boolean isBuyingAtLeastTwoSoups(List<BasketItem> basketItems) {
        return basketItems.stream().filter(item -> {
            return item.getItem().equals(StockItem.SOUP);
        }).findFirst().orElse(new BasketItem(StockItem.SOUP, 1)).getQuantity() >= 2;
    }

    private boolean isBuyingBread(List<BasketItem> basketItems) {
        return basketItems.stream().anyMatch(item -> {
            return item.getItem().equals(StockItem.BREAD);
        });
    }

}
