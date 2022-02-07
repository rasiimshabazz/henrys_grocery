package com.henrys;

import java.time.LocalDate;
import java.util.List;

public interface Coupon {
    double calculateDiscount(List<BasketItem> basketItems, LocalDate purchaseDate);
}
