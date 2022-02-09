package com.henrys.pricer;

import java.time.LocalDate;
import java.util.List;

public abstract class Coupon {

    abstract double calculateDiscount(List<BasketEntry> basketEntries, LocalDate purchaseDate);

    static boolean isNotApplicable(LocalDate purchaseDate, LocalDate validFromDate, LocalDate validToDate) {

        if (purchaseDate == null || validFromDate == null || validToDate == null) return true;

        return !(purchaseDate.isAfter(validFromDate) && purchaseDate.isBefore(validToDate));

    }

    public static Coupon createApplesCoupon(LocalDate validFromDate, LocalDate validToDate) {
        return new ApplesCoupon(validFromDate, validToDate);
    }

    public static Coupon createBreadCoupon(LocalDate validFromDate, LocalDate validToDate) {
        return new BreadCoupon(validFromDate, validToDate);
    }
}
