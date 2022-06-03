package com.henrys.coupon;

import com.henrys.basket.BasketEntries;
import java.time.LocalDate;

public abstract class Coupon {

    protected LocalDate validFromDate;
    protected LocalDate validToDate;

    protected Coupon(LocalDate validFromDate, LocalDate validToDate) {
        this.validFromDate = validFromDate;
        this.validToDate = validToDate;
    }

    public abstract double calculateDiscount(BasketEntries basketEntries, LocalDate purchaseDate);

    protected boolean isApplicable(LocalDate purchaseDate) {
        if (purchaseDate == null || this.validFromDate == null || this.validToDate == null) {
            return false;
        }
        return !purchaseDate.isBefore(this.validFromDate) && !purchaseDate.isAfter(this.validToDate);
    }
}
