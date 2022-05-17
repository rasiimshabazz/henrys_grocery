package com.henrys.basket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class BasketEntriesTest {

    @Test
    void test_compiles() {
        Assertions.assertNotNull(new BasketEntries(Arrays.asList(
                new BasketEntry(StockItem.SOUP, 1),
                new BasketEntry(StockItem.SOUP, 1),
                new BasketEntry(StockItem.SOUP, 1),
                new BasketEntry(StockItem.BREAD, 1),
                new BasketEntry(StockItem.BREAD, 1)
        )));
    }

}
