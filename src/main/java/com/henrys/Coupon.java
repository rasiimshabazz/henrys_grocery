package com.henrys;

import java.util.List;

public interface Coupon {
    double applyDiscount(List<BasketItem> basketItems);
}
