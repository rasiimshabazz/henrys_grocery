package com.henrys;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CashierTest {

    @Test
    @DisplayName("a null basket, costs nada")
    void test_priceBasket_null() {

        assertEquals(format(0), new Cashier().priceBasket(null, false));
    }

    @Test
    @DisplayName("a basket containing nada, costs nada")
    void test_priceBasket_nada() {

        assertEquals(format(0), new Cashier().priceBasket(new Basket(new ArrayList<>()), false));
    }

    @Test
    @DisplayName("a basket containing 1 tin of soup, costs 0.65")
    void test_priceBasket() {

        Basket basket = new Basket(Arrays.asList(
                new BasketItem(StockItem.SOUP, 1)
        ));

        assertEquals(format(0.65), new Cashier().priceBasket(basket, false));
    }

    @Test
    @DisplayName("a basket containing 7 tins of soup, costs 4.55")
    void test_priceBasket_multiple() {

        Basket basket = new Basket(Arrays.asList(
                new BasketItem(StockItem.SOUP, 7)
        ));

        assertEquals(format(4.55), new Cashier().priceBasket(basket, false));
    }

    @Test
    @DisplayName("a basket containing 3 tins of soup and 2 loaves of bread, no promo, costs 3.55")
    void test_priceBasket_multiple_combo() {

        Basket basket = new Basket(Arrays.asList(
                new BasketItem(StockItem.SOUP, 3),
                new BasketItem(StockItem.BREAD, 2)
        ));

        assertEquals(format(3.55), new Cashier().priceBasket(basket, false));
    }

    @Test
    @DisplayName("a basket containing 3 tins of soup and 2 loaves of bread, fixed promo, costs 3.15")
    void test_priceBasket_multiple_fixed_promo() {

        Basket basket = new Basket(Arrays.asList(
                new BasketItem(StockItem.SOUP, 3),
                new BasketItem(StockItem.BREAD, 2)
        ));
        assertEquals(format(3.15), new Cashier().priceBasket(basket, true));
    }

    @Test
    @DisplayName("a basket containing 2 tins of soup and 1 loaves of bread, fixed promo, costs 1.70")
    void test_priceBasket_multiple_fixed_promo_soup_bread_variation() {

        Basket basket = new Basket(Arrays.asList(
                new BasketItem(StockItem.SOUP, 2),
                new BasketItem(StockItem.BREAD, 1)
        ));

        assertEquals(format(1.70), new Cashier().priceBasket(basket, true));
    }

    private BigDecimal format(double value) {
        return BigDecimal.valueOf(value).setScale(2);
    }

}
