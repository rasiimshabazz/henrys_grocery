package com.henrys;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShopperTest {

    Shopper shopper = new Shopper();

    @Test
    void getBasket() {

        Basket actualResult = shopper.goGroceryShopping();

        assertEquals(
                new Basket(null, null).price(Collections.singletonList(createBreadCoupon())),
                actualResult.price(Collections.singletonList(createBreadCoupon())));
    }

    private Coupon createBreadCoupon() {
        return Coupon.createBreadCoupon(
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(1).plusDays(7));
    }

}

