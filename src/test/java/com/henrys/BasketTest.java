package com.henrys;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BasketTest {

    @Test
    @DisplayName("can add and get items from basket")
    void test_getItems() {

        Basket basket = new Basket(Arrays.asList(
                new BasketItem(StockItem.SOUP, 1),
                null,
                new BasketItem(StockItem.BREAD, 1)
        ));

        assertEquals(StockItem.SOUP, basket.getItems().get(0).getItem());
        assertEquals(StockItem.BREAD, basket.getItems().get(1).getItem());
        assertEquals(2, basket.getItems().size());

    }

}