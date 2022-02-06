package com.henrys;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasketTest {

    @Test
    @DisplayName("can add items to basket")
    void test_addItem() {

        Basket basket = new Basket();
        String item = "soup";

        assertTrue(basket.addItem(item));
        assertFalse(basket.addItem(null));
    }

}