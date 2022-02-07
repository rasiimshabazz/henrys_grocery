package com.henrys;

import java.time.LocalDate;
import java.util.List;

public interface Coupon {

    double calculateDiscount(List<BasketItem> basketItems, LocalDate purchaseDate);

    static boolean isValid(LocalDate purchaseDate, LocalDate validFromDate, LocalDate validToDate) {

        if (validFromDate == null || validToDate == null) return true;

        return purchaseDate.isAfter(validFromDate) && purchaseDate.isBefore(validToDate);
    }

}
