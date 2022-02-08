package com.henrys.basket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShopperTest {

    @Test
    @DisplayName("shop")
    void test_goGroceryShopping() {

        Basket expectedBasket = new Basket(new ArrayList<>(), LocalDate.now());
        Basket actualBasket = new Shopper().goGroceryShopping();

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

