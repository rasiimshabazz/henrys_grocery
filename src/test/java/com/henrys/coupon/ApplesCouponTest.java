package com.henrys.coupon;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ApplesCouponTest {

    @Test
    void isApplicable_purchaseDate_within_range() {

        LocalDate purchaseDate = LocalDate.now();
        ApplesCoupon applesCoupon = new ApplesCoupon(purchaseDate.minusDays(1), purchaseDate.plusDays((1)));
        assertTrue(applesCoupon.isApplicable(purchaseDate));
    }

    @Test
    void isApplicable_purchaseDate_within_range_inclusive() {

        LocalDate purchaseDate = LocalDate.now();
        ApplesCoupon applesCoupon = new ApplesCoupon(purchaseDate, purchaseDate.plusDays((1)));
        assertTrue(applesCoupon.isApplicable(purchaseDate));
    }

}