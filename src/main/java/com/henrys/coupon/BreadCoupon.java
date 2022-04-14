package com.henrys.coupon;

import com.henrys.basket.BasketEntry;
import com.henrys.basket.StockItem;

import java.time.LocalDate;
import java.util.List;

public class BreadCoupon extends Coupon {

    private static final double DISCOUNT_FACTOR = 0.5;
    public static final int DISCOUNT_SOUP_QUANTITY = 2;

    public BreadCoupon(LocalDate validFromDate, LocalDate validToDate) {
        super(validFromDate, validToDate, "");
    }

    @Override
    public double calculateDiscount(List<BasketEntry> items, LocalDate purchaseDate) {
        if (purchaseDate == null || this.validFromDate == null || this.validToDate == null) {
            return 0;
        }
        if (purchaseDate.isBefore(this.validFromDate) || purchaseDate.isAfter(this.validToDate)) {
            return 0;
        }
        if (items.stream().anyMatch(item1 -> item1.getItem().equals(StockItem.BREAD)) && items.stream()
                .filter(item -> StockItem.SOUP.equals(item.getItem()))
                .findFirst().orElse(new BasketEntry(StockItem.SOUP, 0))
                .getQuantity() >= DISCOUNT_SOUP_QUANTITY) {
            return StockItem.BREAD.getCost() * DISCOUNT_FACTOR;
        }
        return 0;
    }

}
