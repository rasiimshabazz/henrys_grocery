package com.henrys.coupon;

import com.henrys.basket.BasketEntry;
import com.henrys.basket.StockItem;

import java.time.LocalDate;
import java.util.List;

public class Coupon {

    private String type;
    protected LocalDate validFromDate;
    protected LocalDate validToDate;

    private static final double APPLE_DISCOUNT_FACTOR = .01;
    private static final double BREAD_DISCOUNT_FACTOR = 0.5;
    public static final int BREAD_DISCOUNT_SOUP_QUANTITY = 2;

    public Coupon(LocalDate validFromDate, LocalDate validToDate, String type) {
        this.validFromDate = validFromDate;
        this.validToDate = validToDate;
        this.type = type;
    }

    public double calculateDiscount(List<BasketEntry> basketEntries, LocalDate purchaseDate) {
        if ("bread".equalsIgnoreCase(this.type))
            return calculateBreadDiscount(basketEntries, purchaseDate);
        return calculateAppleDiscount(basketEntries, purchaseDate);
    }

    public double calculateAppleDiscount(List<BasketEntry> items, LocalDate purchaseDate) {
        if (purchaseDate == null || this.validFromDate == null || this.validToDate == null) {
            return 0;
        }
        if (purchaseDate.isBefore(this.validFromDate) || purchaseDate.isAfter(this.validToDate)) {
            return 0;
        }
        return items.stream().filter(item ->
                item.getItem().equals(StockItem.APPLES)
        ).findFirst().orElse(new BasketEntry(StockItem.APPLES, 0)).getQuantity() * APPLE_DISCOUNT_FACTOR;
    }


    public double calculateBreadDiscount(List<BasketEntry> items, LocalDate purchaseDate) {
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
