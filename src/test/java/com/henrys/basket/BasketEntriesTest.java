package com.henrys.basket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class BasketEntriesTest {

    public BasketEntries basketEntries;

    @BeforeEach
    void setup() {
        basketEntries = new BasketEntries(Arrays.asList(
            new BasketEntry(StockItem.SOUP, 1),
            new BasketEntry(StockItem.SOUP, 1),
            new BasketEntry(StockItem.SOUP, 1),
            new BasketEntry(StockItem.BREAD, 1),
            new BasketEntry(StockItem.BREAD, 1)));
    }

    @Test
    void test_stringValue() {
        Assertions.assertEquals("3 SOUP, 2 BREAD", basketEntries.stringValue());
    }

    @Test
    void test_size() {
        Assertions.assertEquals(2, basketEntries.countAll());
    }

    @Test
    void test_add() {
        basketEntries.add(new BasketEntry(StockItem.BREAD, 1));
        Assertions.assertEquals(2, basketEntries.countAll());
        basketEntries.add(new BasketEntry(StockItem.APPLES, 1));
        Assertions.assertEquals(3, basketEntries.countAll());
    }
}
