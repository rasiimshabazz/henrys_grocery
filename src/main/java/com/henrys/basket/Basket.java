package com.henrys.basket;

import com.henrys.coupon.Coupons;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Collections;

public class Basket {

    private final BasketEntries basketEntries;

    private final LocalDate purchaseDate;

    public Basket(BasketEntries basketEntries, LocalDate purchaseDate) {
        if (basketEntries == null) basketEntries = new BasketEntries(Collections.emptyList());
        this.basketEntries = basketEntries;
        this.purchaseDate = purchaseDate;
    }

    public BigDecimal priceBasket(Coupons coupons) {
        double price = this.basketEntries.price() - coupons.discount(this.basketEntries, this.purchaseDate);
        return BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP);
    }

    public String toString() {
        return "items: " + this.basketEntries.toString() + ", purchase date: " + this.purchaseDate.toString();
    }
}
