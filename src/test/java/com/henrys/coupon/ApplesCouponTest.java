package com.henrys.coupon;

import com.henrys.basket.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

class ApplesCouponTest {

    @Test
    void calculateDiscount_valid_coupon() {
        BasketEntries basketEntries = new BasketEntries(Arrays.asList(new BasketEntry(StockItem.APPLES, 6)));
        LocalDate purchaseDate = LocalDate.now();
        ApplesCoupon coupon = new ApplesCoupon(purchaseDate.minusDays(1), purchaseDate.plusDays(1));
        Assertions.assertEquals(0.06, coupon.calculateDiscount(basketEntries, purchaseDate));
    }

    @Test
    void calculateDiscount_invalid_coupon() {
        BasketEntries basketEntries = new BasketEntries(Arrays.asList(new BasketEntry(StockItem.APPLES, 6)));
        LocalDate purchaseDate = LocalDate.now();
        ApplesCoupon coupon = new ApplesCoupon(purchaseDate.minusDays(1), purchaseDate.minusDays(1));
        Assertions.assertEquals(0.0, coupon.calculateDiscount(basketEntries, purchaseDate));
    }

}