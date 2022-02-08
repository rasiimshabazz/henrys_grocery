package com.henrys;

import java.time.LocalDate;
import java.util.List;

abstract class Coupon {

    abstract double calculateDiscount(List<BasketItem> basketItems, LocalDate purchaseDate);

    static boolean isValid(LocalDate purchaseDate, LocalDate validFromDate, LocalDate validToDate) {

        boolean couponDoesNotExpire = isCouponDoesNotExpire(validFromDate, validToDate);
        if (couponDoesNotExpire) return true;

        return purchaseDate.isAfter(validFromDate) && purchaseDate.isBefore(validToDate);
    }

    @Deprecated
    static boolean isCouponDoesNotExpire(LocalDate validFromDate, LocalDate validToDate) {
        boolean couponDoesNotExpire = validFromDate == null || validToDate == null;
        return couponDoesNotExpire;
    }

}
