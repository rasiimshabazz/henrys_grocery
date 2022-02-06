package com.henrys;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasketTest {

    @Test
    @DisplayName("can add and get items from basket")
    void test_getItems() {

        Basket basket = new Basket();

        assertTrue(basket.addItem(new BasketItem(StockItem.SOUP, 1)));
        assertFalse(basket.addItem(null));
        assertTrue(basket.addItem(new BasketItem(StockItem.BREAD, 1)));

        assertEquals(StockItem.SOUP, basket.getItems().get(0).getItem());
        assertEquals(StockItem.BREAD, basket.getItems().get(1).getItem());
        assertEquals(2, basket.getItems().size());

    }

}