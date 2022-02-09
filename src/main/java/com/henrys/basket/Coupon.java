package com.henrys.basket;

import java.time.LocalDate;
import java.util.List;

public abstract class Coupon {

    protected LocalDate validFromDate;
    protected LocalDate validToDate;

    protected Coupon(LocalDate validFromDate, LocalDate validToDate) {
        this.validFromDate = validFromDate;
        this.validToDate = validToDate;
    }

    abstract double calculateDiscount(List<BasketEntry> basketEntries, LocalDate purchaseDate);

    boolean isApplicable(LocalDate purchaseDate, LocalDate validFromDate, LocalDate validToDate) {

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
}
