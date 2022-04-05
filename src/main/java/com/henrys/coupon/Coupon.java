package com.henrys.coupon;

import com.henrys.basket.BasketEntry;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;

public abstract class Coupon {

    protected LocalDate validFromDate;
    protected LocalDate validToDate;

    protected Coupon(LocalDate validFromDate, LocalDate validToDate) {
        this.validFromDate = validFromDate;
        this.validToDate = validToDate;
    }

    public abstract double calculateDiscount(List<BasketEntry> basketEntries, LocalDate purchaseDate);

    protected boolean isApplicable(LocalDate purchaseDate, LocalDate validFromDate, LocalDate validToDate) {
        if (purchaseDate == null || validFromDate == null || validToDate == null) {
            return false;
        }
        return purchaseDate.isAfter(validFromDate) && purchaseDate.isBefore(validToDate);
    }

    public static Coupon createApplesCoupon(LocalDate validFromDate, LocalDate validToDate) {
        return new ApplesCoupon(validFromDate, validToDate);
    }

    public static Coupon createBreadCoupon(LocalDate validFromDate, LocalDate validToDate) {
        return new BreadCoupon(validFromDate, validToDate);
    }

    public static List<Coupon> currentPromotion() {
        return Arrays.asList(
                Coupon.createBreadCoupon(
                        LocalDate.now().minusDays(1),
                        LocalDate.now().minusDays(1).plusDays(7)),
                Coupon.createApplesCoupon(
                        LocalDate.now().plusDays(3),
                        LocalDate.now().plusDays(3).plusMonths(1).with(TemporalAdjusters.lastDayOfMonth())));
    }
}
