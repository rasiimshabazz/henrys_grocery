package com.henrys;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HenryTest {

    @Test
    public void test_priceBasket() {

        Henry henry = new Henry();
        Basket basket = new Basket();

        assertEquals(0.15, henry.priceBasket(basket));
    }
}
