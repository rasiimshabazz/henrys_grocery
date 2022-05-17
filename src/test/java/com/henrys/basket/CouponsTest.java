package com.henrys.basket;

import com.henrys.coupon.Coupons;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class CouponsTest {

    @Test
    void test_compiles() {

        Assertions.assertNotNull(new Coupons(Collections.emptyList()));
    }
}
