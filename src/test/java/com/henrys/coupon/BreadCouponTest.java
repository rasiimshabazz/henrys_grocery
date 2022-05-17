package com.henrys.coupon;

import com.henrys.basket.BasketEntries;
import com.henrys.basket.BasketEntry;
import com.henrys.basket.StockItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

class BreadCouponTest {

    @Test
    void calculateDiscount_valid_coupon() {
        BasketEntries basketEntries = new BasketEntries(Arrays.asList(
                new BasketEntry(StockItem.SOUP, 2),
                new BasketEntry(StockItem.BREAD, 1)
        ));
        LocalDate purchaseDate = LocalDate.now();
        BreadCoupon coupon = new BreadCoupon(purchaseDate.minusDays(1), purchaseDate.plusDays(1));
        Assertions.assertEquals(0.4, coupon.calculateDiscount(basketEntries, purchaseDate));
    }

    @Test
    void calculateDiscount_invalid_coupon() {
        BasketEntries basketEntries = new BasketEntries(Arrays.asList(
                new BasketEntry(StockItem.SOUP, 2),
                new BasketEntry(StockItem.BREAD, 1)
        ));
        LocalDate purchaseDate = LocalDate.now();
        BreadCoupon coupon = new BreadCoupon(purchaseDate.minusDays(1), purchaseDate.minusDays(1));
        Assertions.assertEquals(0.0, coupon.calculateDiscount(basketEntries, purchaseDate));
    }

}