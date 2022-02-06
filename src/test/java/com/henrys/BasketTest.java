package com.henrys;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasketTest {

    @Test
    @DisplayName("can add and get items from basket")
    void test_getItems() {

        Basket basket = new Basket();

        assertTrue(basket.addItem("soup"));
        assertFalse(basket.addItem(null));
        assertTrue(basket.addItem("bread"));

        assertEquals("soup", basket.getItems().get(0));
        assertEquals("bread", basket.getItems().get(1));
        assertEquals(2, basket.getItems().size());
    }

}