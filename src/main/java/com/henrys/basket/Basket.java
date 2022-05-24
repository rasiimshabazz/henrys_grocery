package com.henrys.basket;

import com.henrys.coupon.Coupons;

import java.time.LocalDate;
import java.util.Collections;

public class Basket {

    private final BasketEntries entries;

    private final LocalDate purchaseDate;

    public Basket(BasketEntries entries, LocalDate purchaseDate) {
        if (entries == null) entries = new BasketEntries(Collections.emptyList());
        this.entries = entries;
        this.purchaseDate = purchaseDate;
    }

    public Price price(Coupons coupons) {
        double price = this.entries.price() - coupons.discount(this.entries, this.purchaseDate);
        return new Price(price);
    }

    public String stringValue() {
        return "items: " + this.entries.stringValue() + ", purchase date: " + this.purchaseDate.toString();
    }
}
