package com.henrys.coupon;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;

public class CouponFactory {

    public static Coupon createApplesCoupon(LocalDate validFromDate, LocalDate validToDate) {
        return new ApplesCoupon(validFromDate, validToDate);
    }

    public static Coupon createBreadCoupon(LocalDate validFromDate, LocalDate validToDate) {
        return new BreadCoupon(validFromDate, validToDate);
    }

    public static List<Coupon> createCurrentPromotion() {
        return Arrays.asList(
                createBreadCoupon(
                        LocalDate.now().minusDays(1),
                        LocalDate.now().minusDays(1).plusDays(7)),
                createApplesCoupon(
                        LocalDate.now().plusDays(3),
                        LocalDate.now().plusDays(3).plusMonths(1).with(TemporalAdjusters.lastDayOfMonth())));
    }
}
