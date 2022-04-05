package com.henrys.coupon;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ApplesCouponTest {

    @Test
    void isApplicable_purchaseDate_within_range() {

        ApplesCoupon coupon = new ApplesCoupon(LocalDate.now().minusDays(1), LocalDate.now().plusDays((1)));

        LocalDate.now();
        LocalDate.now();
        assertTrue(coupon.isApplicableHelper(LocalDate.now()));
    }
}