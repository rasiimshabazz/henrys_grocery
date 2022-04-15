package com.henrys.coupon;

import com.henrys.basket.BasketEntry;
import com.henrys.basket.StockItem;

import java.time.LocalDate;
import java.util.List;

public class Coupon {

    private String type;
    private LocalDate validFromDate;
    private LocalDate validToDate;

    public Coupon(LocalDate validFromDate, LocalDate validToDate, String type) {
        this.validFromDate = validFromDate;
        this.validToDate = validToDate;
        this.type = type;
    }

    public double calculateDiscount(List<BasketEntry> basketEntries, LocalDate purchaseDate) {
        if ("bread".equalsIgnoreCase(this.type)) {
            if (purchaseDate == null || this.validFromDate == null || this.validToDate == null) {
                return 0;
            }
            if (purchaseDate.isBefore(this.validFromDate) || purchaseDate.isAfter(this.validToDate)) {
                return 0;
            }
            if (basketEntries.stream().anyMatch(item1 -> item1.item.equals(StockItem.BREAD)) && basketEntries.stream()
                    .filter(item -> StockItem.SOUP.equals(item.item))
                    .findFirst().orElse(new BasketEntry(StockItem.SOUP, 0)).quantity >= 2) {
                return StockItem.BREAD.cost * 0.5;
            }
            return 0;
        }
        if (purchaseDate == null || this.validFromDate == null || this.validToDate == null) {
            return 0;
        }
        if (purchaseDate.isBefore(this.validFromDate) || purchaseDate.isAfter(this.validToDate)) {
            return 0;
        }
        return basketEntries.stream().filter(item -> item.item.equals(StockItem.APPLES)
        ).findFirst().orElse(new BasketEntry(StockItem.APPLES, 0)).quantity * .01;
    }

}
