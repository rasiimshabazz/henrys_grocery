package com.henrys.coupon;

import com.henrys.basket.BasketEntry;

import java.time.LocalDate;
import java.util.List;

public abstract class Coupon {

    protected LocalDate validFromDate;
    protected LocalDate validToDate;

    protected Coupon(LocalDate validFromDate, LocalDate validToDate) {
        this.validFromDate = validFromDate;
        this.validToDate = validToDate;
    }

    public abstract double calculateDiscount(List<BasketEntry> basketEntries, LocalDate purchaseDate);

}
