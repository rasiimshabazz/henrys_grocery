package com.henrys.basket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PriceTest {

    @Test
    void test_compiles() {
        Assertions.assertNotNull(new Price(Double.MIN_VALUE));
    }
}
