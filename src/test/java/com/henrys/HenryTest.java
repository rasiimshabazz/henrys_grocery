package com.henrys;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HenryTest {

    @Test
    @DisplayName("a basket containing 1 tin of soup, costs 0.65")
    public void test_priceBasket() {

        Henry henry = new Henry();
        Basket basket = new Basket();
        basket.addItem("soup");

        double price = henry.priceBasket(basket);

        assertEquals(0.65, price);
    }
}
