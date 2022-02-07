package com.henrys;

import java.util.List;

public interface Coupon {
    double calculateDiscount(List<BasketItem> basketItems);
}
