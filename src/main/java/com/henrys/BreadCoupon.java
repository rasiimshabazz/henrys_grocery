package com.henrys;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

class BreadCoupon implements Coupon {

    private LocalDate validFromDate;
    private LocalDate validToDate;

    BreadCoupon() {
    }

    BreadCoupon(LocalDate validFromDate, LocalDate validToDate) {
        this.validFromDate = validFromDate;
        this.validToDate = validToDate;
    }

    @Override
    public double calculateDiscount(List<BasketItem> basketItems, LocalDate purchaseDate) {

        if (!isValid(purchaseDate)) return 0;

        boolean isBuyingBread = basketItems.stream().anyMatch(item -> {
            return item.getItem().equals(StockItem.BREAD);
        });

        boolean isBuyingAtLeastTwoSoups = basketItems.stream().filter(item -> {
            return item.getItem().equals(StockItem.SOUP);
        }).findFirst().orElse(new BasketItem(StockItem.SOUP, 1)).getQuantity() >= 2;

        if (isBuyingBread && isBuyingAtLeastTwoSoups)
            return StockItem.BREAD.getCost() / 2;

        return 0;
    }

    public boolean isValid(LocalDate purchaseDate) {

        if (this.validFromDate == null || this.validToDate == null) return true;

        return purchaseDate.isAfter(this.validFromDate) && purchaseDate.isBefore(this.validToDate);
    }
}
