package com.henrys;

import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("shop")
    void test_goGroceryShopping() {

        Basket actualBasket = shopper.goGroceryShopping();
        Basket expectedBasket = new Basket(null, null);

        assertEquals(
                expectedBasket.price(testBreadCoupon()),
                actualBasket.price(testBreadCoupon()));
    }

    private List<Coupon> testBreadCoupon() {
        return Collections.singletonList(Coupon.createBreadCoupon(
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(1).plusDays(7)));
    }

}

