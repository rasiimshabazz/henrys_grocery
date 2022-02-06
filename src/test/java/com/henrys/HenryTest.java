package com.henrys;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HenryTest {

    @Test
    @DisplayName("can setup basic basket")
    public void test_priceBasket() {

        Henry henry = new Henry();
        Basket basket = new Basket();

        double price = henry.priceBasket(basket);

        assertEquals(0.15, price);
    }
}
