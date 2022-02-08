package com.henrys;

import java.time.LocalDate;
import java.util.List;

abstract class Coupon {

    abstract double calculateDiscount(List<BasketItem> basketItems, LocalDate purchaseDate);

    static boolean isNotApplicable(LocalDate purchaseDate, LocalDate validFromDate, LocalDate validToDate) {
        return !purchaseDate.isAfter(validFromDate) || !purchaseDate.isBefore(validToDate);
    }

    static Coupon createApplesCoupon(LocalDate validFromDate, LocalDate validToDate) {
        return new ApplesCoupon(validFromDate, validToDate);
    }

    static Coupon createBreadCoupon(LocalDate validFromDate, LocalDate validToDate) {
        return new BreadCoupon(validFromDate, validToDate);
    }

}
