package com.henrys.coupon;

import com.henrys.basket.BasketEntry;
import com.henrys.basket.StockItem;

import java.time.LocalDate;
import java.util.List;

public class BreadCoupon extends Coupon {

    public BreadCoupon(LocalDate validFromDate, LocalDate validToDate) {
        super(validFromDate, validToDate, "");
    }

    private static final double BREAD_DISCOUNT_FACTOR = 0.5;
    public static final int BREAD_DISCOUNT_SOUP_QUANTITY = 2;

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
                .getQuantity() >= BREAD_DISCOUNT_SOUP_QUANTITY) {
            return StockItem.BREAD.getCost() * BREAD_DISCOUNT_FACTOR;
        }
        return 0;
    }

}
