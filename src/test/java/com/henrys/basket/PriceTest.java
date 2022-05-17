package com.henrys.basket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceTest {

    @Test
    void test_value() {

        double decimal = 10.0;
        Assertions.assertEquals(BigDecimal.valueOf(decimal).setScale(2, RoundingMode.HALF_UP),
                new Price(decimal).value());
    }
}
