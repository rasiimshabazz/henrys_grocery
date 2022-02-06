package com.henrys;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class HenryTest {

    @Test
    @DisplayName("a basket containing nada, costs nada")
    void test_priceBasket_nada() {

        assertEquals(BigDecimal.valueOf(0), new Henry().priceBasket(new Basket()));
    }

    @Test
    @DisplayName("a basket containing 1 tin of soup, costs 0.65")
    void test_priceBasket() {

        Basket basket = new Basket();
        basket.addItem(new BasketItem(StockItem.SOUP, 1));

        assertEquals(BigDecimal.valueOf(0.65), new Henry().priceBasket(basket));
    }

    @Test
    @DisplayName("a basket containing 7 tins of soup, costs 4.55")
    void test_priceBasket_multiple() {

        Basket basket = new Basket();
        basket.addItem(new BasketItem(StockItem.SOUP, 7));

        assertEquals(BigDecimal.valueOf(4.55), new Henry().priceBasket(basket));
    }



}
