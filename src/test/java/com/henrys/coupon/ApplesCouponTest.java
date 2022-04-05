package com.henrys.coupon;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ApplesCouponTest {

    @Test
    void calculateDiscount_purchaseDate_within_range() {

        ApplesCoupon coupon = new ApplesCoupon(LocalDate.now().minusDays(1), LocalDate.now().plusDays((1)));

        boolean isApplicable = coupon.isApplicable(LocalDate.now(), LocalDate.now().minusDays(1), LocalDate.now().plusDays((1)));

        assertTrue(isApplicable);
    }
}