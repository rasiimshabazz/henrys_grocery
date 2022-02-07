package com.henrys;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static com.henrys.Cashier.format;
import static org.junit.jupiter.api.Assertions.*;

class CashierTest {

    @Test
    @DisplayName("a null basket, costs nada")
    void test_priceBasket_null() {
        assertEquals(format(0), new Cashier().priceBasket(null, Collections.EMPTY_LIST));
    }

    @Test
    @DisplayName("a basket containing nada, costs nada")
    void test_priceBasket_nada() {

        assertEquals(format(0), new Cashier().priceBasket(new Basket(new ArrayList<>(), LocalDate.now()),
                Collections.EMPTY_LIST));
    }

    @Test
    @DisplayName("a basket containing 1 tin of soup, costs 0.65")
    void test_priceBasket() {

        Basket basket = new Basket(Arrays.asList(
                new BasketItem(StockItem.SOUP, 1)
        ), LocalDate.now());

        assertEquals(format(0.65), new Cashier().priceBasket(basket, Collections.EMPTY_LIST));
    }

    @Test
    @DisplayName("a basket containing 1 tin of soup, null coupons, costs 0.65")
    void test_priceBasket_nullCoupons() {

        Basket basket = new Basket(Arrays.asList(
                new BasketItem(StockItem.SOUP, 1)
        ), LocalDate.now());

        assertEquals(format(0.65), new Cashier().priceBasket(basket, null));
    }

    @Test
    @DisplayName("a basket containing 7 tins of soup, costs 4.55")
    void test_priceBasket_multiple() {

        Basket basket = new Basket(Arrays.asList(
                new BasketItem(StockItem.SOUP, 7)
        ), LocalDate.now());

        assertEquals(format(4.55), new Cashier().priceBasket(basket, Collections.EMPTY_LIST));
    }

    @Test
    @DisplayName("a basket containing 7 tins of soup (merged), costs 4.55")
    void test_priceBasket_multiple_merged() {

        Basket basket = new Basket(Arrays.asList(
                new BasketItem(StockItem.SOUP, 3),
                new BasketItem(StockItem.SOUP, 4)
        ), LocalDate.now());

        assertEquals(format(4.55), new Cashier().priceBasket(basket, Collections.EMPTY_LIST));
    }

    @Test
    @DisplayName("a basket containing 3 tins of soup and 2 loaves of bread, no coupon, costs 3.55")
    void test_priceBasket_multiple_combo() {

        Basket basket = new Basket(Arrays.asList(
                new BasketItem(StockItem.SOUP, 3),
                new BasketItem(StockItem.BREAD, 2)
        ), LocalDate.now());

        assertEquals(format(3.55), new Cashier().priceBasket(basket, Collections.EMPTY_LIST));
    }

    @Test
    @DisplayName("a basket containing 2 tins of soup and 1 loaves of bread, no coupon, costs 2.10")
    void test_priceBasket_multiple_no_promo_soup_bread_variation() {

        Basket basket = new Basket(Arrays.asList(
                new BasketItem(StockItem.SOUP, 2),
                new BasketItem(StockItem.BREAD, 1)
        ), LocalDate.now());

        assertEquals(format(2.10), new Cashier().priceBasket(basket, Collections.EMPTY_LIST));
    }

    @Test
    @DisplayName("a basket containing 3 tins of soup and 2 loaves of bread, fixed coupon, costs 3.15")
    void test_priceBasket_multiple_fixed_promo() {

        Basket basket = new Basket(Arrays.asList(
                new BasketItem(StockItem.SOUP, 3),
                new BasketItem(StockItem.BREAD, 2)
        ), LocalDate.now());
        assertEquals(format(3.15), new Cashier().priceBasket(basket, Arrays.asList(new BreadCoupon())));
    }

    @Test
    @DisplayName("a basket containing 2 tins of soup and 1 loaves of bread, fixed coupon, costs 1.70")
    void test_priceBasket_multiple_fixed_promo_soup_bread_variation() {

        Basket basket = new Basket(Arrays.asList(
                new BasketItem(StockItem.SOUP, 2),
                new BasketItem(StockItem.BREAD, 1)
        ), LocalDate.now());

        assertEquals(format(1.70), new Cashier().priceBasket(basket, Arrays.asList(new BreadCoupon())));
    }

    @Test
    @DisplayName("a basket containing 6 apples and a bottle of milk, costs = 1.90")
    void test_priceBasket_apples_milk_no_coupon() {

        Basket basket = new Basket(Arrays.asList(
                new BasketItem(StockItem.APPLES, 6),
                new BasketItem(StockItem.MILK, 1)
        ), LocalDate.now());

        assertEquals(format(1.90), new Cashier().priceBasket(basket, Collections.EMPTY_LIST));
    }

    @Test
    @DisplayName("a basket containing 6 apples and a bottle of milk, fixed coupon costs = 1.84")
    void test_priceBasket_apples_milk_with_coupon() {

        Basket basket = new Basket(Arrays.asList(
                new BasketItem(StockItem.APPLES, 6),
                new BasketItem(StockItem.MILK, 1)
        ), LocalDate.now());

        assertEquals(format(1.84), new Cashier().priceBasket(basket, Arrays.asList(
                new BreadCoupon(), new ApplesCoupon())));
    }

    @Test
    @DisplayName("a basket containing 3 apples, 2 tins of soup and a loaf of bread, costs = 1.97")
    void test_priceBasket_apples_soup_bread_with_coupon() {

        Basket basket = new Basket(Arrays.asList(
                new BasketItem(StockItem.APPLES, 3),
                new BasketItem(StockItem.SOUP, 2),
                new BasketItem(StockItem.BREAD, 1)
        ), LocalDate.now());

        assertEquals(format(1.97), new Cashier().priceBasket(basket, Arrays.asList(
                new BreadCoupon(), new ApplesCoupon())));

    }

    @Test
    @DisplayName("a basket containing 3 tins of soup and 2 loaves of bread, expiring coupon, bought a week ago, costs 3.55")
    void test_priceBasket_multiple_before_expiring_coupon() {

        LocalDate aWeekAgo = LocalDate.now().minusWeeks(1);
        Basket basket = new Basket(Arrays.asList(
                new BasketItem(StockItem.SOUP, 3),
                new BasketItem(StockItem.BREAD, 2)
        ), aWeekAgo);

        LocalDate validFromYesterday = LocalDate.now().minusDays(1);

        assertEquals(format(3.55), new Cashier().priceBasket(basket, Arrays.asList(
                new BreadCoupon(validFromYesterday, validFromYesterday.plusDays(7)))));
    }

    @Test
    @DisplayName("a basket containing 3 tins of soup and 2 loaves of bread, expiring coupon, bought today, costs 3.15")
    void test_priceBasket_expiring_bread_coupon() {

        Basket basket = new Basket(Arrays.asList(
                new BasketItem(StockItem.SOUP, 3),
                new BasketItem(StockItem.BREAD, 2)
        ), LocalDate.now());

        LocalDate validFromDate = LocalDate.now().minusDays(1);

        assertEquals(format(3.15), new Cashier().priceBasket(basket, Arrays.asList(
                new BreadCoupon(validFromDate, validFromDate.plusDays(7)))));
    }

    @Test
    @DisplayName("a basket containing 6 apples and a bottle of milk, expiring coupon, bought today, costs = 1.90")
    void test_priceBasket_expiring_apple_coupon() {

        Basket basket = new Basket(Arrays.asList(
                new BasketItem(StockItem.APPLES, 6),
                new BasketItem(StockItem.MILK, 1)
        ), LocalDate.now());

        LocalDate threeDaysFromNow = LocalDate.now().plusDays(3);
        LocalDate endOfFollowingMonth = threeDaysFromNow.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

        assertEquals(format(1.90), new Cashier().priceBasket(basket, Arrays.asList(
                new BreadCoupon(), new ApplesCoupon(threeDaysFromNow, endOfFollowingMonth))));
    }


}
