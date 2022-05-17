package com.henrys.coupon;

import com.henrys.basket.BasketEntries;
import com.henrys.basket.BasketEntry;
import com.henrys.basket.StockItem;

import java.time.LocalDate;
class ApplesCoupon extends Coupon {

    private static final double DISCOUNT_FACTOR = .01;

    ApplesCoupon(LocalDate validFromDate, LocalDate validToDate) {
        super(validFromDate, validToDate);
    }

    @Override
    public double calculateDiscount(BasketEntries basketEntries, LocalDate purchaseDate) {
        if (!isApplicable(purchaseDate)) {
            return 0;
        }
        return numberOfApples(basketEntries) * DISCOUNT_FACTOR;
    }

    private int numberOfApples(BasketEntries basketEntries) {
        return numberOfStockItem(basketEntries, StockItem.APPLES);
    }

    private int numberOfStockItem(BasketEntries basketEntries, StockItem stockItem) {
        return basketEntries.getEntries().stream().filter(item ->
                item.getItem().equals(stockItem)
        ).findFirst().orElse(new BasketEntry(stockItem, 0)).getQuantity();
    }

}
