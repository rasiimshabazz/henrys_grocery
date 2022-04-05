package com.henrys.coupon;

import com.henrys.basket.Basket;
import com.henrys.basket.BasketEntry;
import com.henrys.basket.BasketTest;
import com.henrys.basket.StockItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

class ApplesCouponTest {

    @Test
    void calculateDiscount_valid_coupon() {
        List<BasketEntry> basketItems = Arrays.asList(new BasketEntry(StockItem.APPLES, 6));
        LocalDate purchaseDate = LocalDate.now();
        ApplesCoupon coupon = new ApplesCoupon(purchaseDate.minusDays(1), purchaseDate.plusDays(1));
        Assertions.assertEquals(0.06, coupon.calculateDiscount(basketItems, purchaseDate));
    }

    @Test
    void calculateDiscount_invalid_coupon() {
        List<BasketEntry> basketItems = Arrays.asList(new BasketEntry(StockItem.APPLES, 6));
        LocalDate purchaseDate = LocalDate.now();
        ApplesCoupon coupon = new ApplesCoupon(purchaseDate.minusDays(1), purchaseDate.minusDays(1));
        Assertions.assertEquals(0.00, coupon.calculateDiscount(basketItems, purchaseDate));
    }

}