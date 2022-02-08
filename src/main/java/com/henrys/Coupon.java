package com.henrys;

import java.time.LocalDate;
import java.util.List;

public interface Coupon {

    double calculateDiscount(List<BasketItem> basketItems, LocalDate purchaseDate);

    static boolean isValid(LocalDate purchaseDate, LocalDate validFromDate, LocalDate validToDate) {

        boolean couponDoesNotExpire = validFromDate == null || validToDate == null;
        if (couponDoesNotExpire) return true;

        return purchaseDate.isAfter(validFromDate) && purchaseDate.isBefore(validToDate);
    }

}
