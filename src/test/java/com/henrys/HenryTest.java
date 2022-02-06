package com.henrys;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class HenryTest {

    @Test
    @DisplayName("a basket containing nada, costs nada")
    void test_priceBasket_nada() {

        Henry henry = new Henry();
        Basket basket = new Basket();

        assertEquals(BigDecimal.valueOf(0), henry.priceBasket(basket));
    }

    @Test
    @DisplayName("a basket containing 1 tin of soup, costs 0.65")
    void test_priceBasket() {

        Henry henry = new Henry();
        Basket basket = new Basket();
        basket.addItem(StockItem.SOUP);

        assertEquals(BigDecimal.valueOf(0.65), henry.priceBasket(basket));
    }
}
