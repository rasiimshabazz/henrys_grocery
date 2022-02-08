package com.henrys;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShopperTest {

    @Test
    void getBasket() {

        List<Coupon> coupons = null;
        assertEquals(new Basket(null, null).price(coupons), new Shopper().goGroceryShopping().price(coupons));
    }
}

