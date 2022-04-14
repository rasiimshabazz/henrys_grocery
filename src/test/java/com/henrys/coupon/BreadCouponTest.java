package com.henrys.coupon;

import com.henrys.basket.BasketEntry;
import com.henrys.basket.StockItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

class BreadCouponTest {

    @Test
    void calculateDiscount_valid_coupon() {
        List<BasketEntry> basketItems = Arrays.asList(
                new BasketEntry(StockItem.SOUP, 2),
                new BasketEntry(StockItem.BREAD, 1)
        );
        LocalDate purchaseDate = LocalDate.now();
        Coupon coupon = new Coupon(purchaseDate.minusDays(1), purchaseDate.plusDays(1), Coupon.COUPON_IND_BREAD);
        Assertions.assertEquals(0.4, coupon.calculateDiscount(basketItems, purchaseDate));
    }

    @Test
    void calculateDiscount_invalid_coupon() {
        List<BasketEntry> basketItems = Arrays.asList(
                new BasketEntry(StockItem.SOUP, 2),
                new BasketEntry(StockItem.BREAD, 1)
        );
        LocalDate purchaseDate = LocalDate.now();
        Coupon coupon = new Coupon(purchaseDate.minusDays(1), purchaseDate.minusDays(1), Coupon.COUPON_IND_BREAD);
        Assertions.assertEquals(0.0, coupon.calculateDiscount(basketItems, purchaseDate));
    }

}