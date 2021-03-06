package com.henrys.basket;

import com.henrys.coupon.Coupon;
import com.henrys.coupon.CouponFactory;
import com.henrys.coupon.Coupons;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BasketTest {

    @Test
    @DisplayName("a basket containing null, costs nada")
    void test_calculatePrice_of_null() {
        Assertions.assertEquals(new Price(0).value(),
                new Basket(null, null).price(new Coupons(new ArrayList<Coupon>())).value());
    }

    @Test
    @DisplayName("* a basket containing 3 tins of soup (unmerged up) and 2 loaves of bread, bought today, costs 3.15")
    void test_calculatePrice_bread_coupon_rolled_up() {

        Basket basket = new Basket(new BasketEntries(Arrays.asList(
                new BasketEntry(StockItem.SOUP, 1),
                new BasketEntry(StockItem.SOUP, 1),
                new BasketEntry(StockItem.SOUP, 1),
                new BasketEntry(StockItem.BREAD, 1),
                new BasketEntry(StockItem.BREAD, 1))
        ), LocalDate.now());

        Assertions.assertEquals(new Price(3.15).value(), basket.price(new Coupons(Collections.singletonList(
                createBreadCoupon()))).value());
    }

    @Test
    @DisplayName("a basket containing -3 tins of soup (unmerged up) and -2 loaves of bread, bought today, costs nada")
    void test_calculatePrice_bread_coupon_rolled_up_negative() {

        Basket basket = new Basket(new BasketEntries(Arrays.asList(
                new BasketEntry(StockItem.SOUP, -1),
                new BasketEntry(StockItem.SOUP, -1),
                new BasketEntry(StockItem.SOUP, -1),
                new BasketEntry(StockItem.BREAD, -1),
                new BasketEntry(StockItem.BREAD, -1)
        )), LocalDate.now());

        Assertions.assertEquals(new Price(0.00).value(), basket.price(new Coupons(Collections.singletonList(
                createBreadCoupon()))).value());
    }

    @Test
    @DisplayName("* a basket containing 6 apples and a bottle of milk, bought today, costs = 1.90")
    void test_calculatePrice_apple_coupon_invalid() {

        Basket basket = new Basket(new BasketEntries(Arrays.asList(
                new BasketEntry(StockItem.APPLES, 6),
                new BasketEntry(StockItem.MILK, 1)
        )), LocalDate.now());

        Assertions.assertEquals(new Price(1.90).value(), basket.price(new Coupons(Arrays.asList(
                createBreadCoupon(),
                createApplesCoupon()))).value());
    }

    @Test
    @DisplayName("* a basket containing 6 apples and a bottle of milk, bought in 5 days time, costs = 1.84")
    void test_calculatePrice_apple_coupon_valid() {

        LocalDate boughtInFiveDaysTime = LocalDate.now().plusDays(5);
        Basket basket = new Basket(new BasketEntries(Arrays.asList(
                new BasketEntry(StockItem.APPLES, 6),
                new BasketEntry(StockItem.MILK, 1)
        )), boughtInFiveDaysTime);

        Assertions.assertEquals(new Price(1.84).value(), basket.price(new Coupons(Arrays.asList(
                createBreadCoupon(),
                createApplesCoupon()))).value());
    }

    @Test
    @DisplayName("* a basket containing 3 apples, 2 tins of soup and a loaf of bread, bought in 5 days time, costs = 1.97")
    void test_calculatePrice_apples_soup_bread_with_coupons_valid() {

        LocalDate boughtInFiveDaysTime = LocalDate.now().plusDays(5);
        Basket basket = new Basket(new BasketEntries(Arrays.asList(
                new BasketEntry(StockItem.APPLES, 3),
                new BasketEntry(StockItem.SOUP, 2),
                new BasketEntry(StockItem.BREAD, 1)
        )), boughtInFiveDaysTime);

        Assertions.assertEquals(new Price(1.97).value(), basket.price(new Coupons(Arrays.asList(createBreadCoupon(),
                createApplesCoupon()))).value());
    }

    @Test
    @DisplayName("a basket containing 3 tins of soup and 2 loaves of bread, bought a week ago, costs 3.55")
    void test_calculatePrice_multiple_before_coupon() {

        LocalDate boughtAWeekAgo = LocalDate.now().minusWeeks(1);
        Basket basket = new Basket(new BasketEntries(Arrays.asList(
                new BasketEntry(StockItem.SOUP, 3),
                new BasketEntry(StockItem.BREAD, 2)
        )), boughtAWeekAgo);

        Assertions.assertEquals(new Price(3.55).value(), basket.price(new Coupons(Collections.singletonList(
                createBreadCoupon()))).value());
    }

    @Test
    @DisplayName("a basket containing nada, costs nada")
    void test_calculatePrice_of_nada() {

        Assertions.assertEquals(new Price(0).value(),
                new Basket(new BasketEntries(new ArrayList<>()), LocalDate.now()).price(new Coupons(new ArrayList<Coupon>())).value());
    }

    @Test
    @DisplayName("a basket containing 1 tin of soup, no coupon, bought today, costs 0.65")
    void test_calculatePrice_soup_no_coupons() {

        Basket basket = new Basket(new BasketEntries(Collections.singletonList(
                new BasketEntry(StockItem.SOUP, 1)
        )), LocalDate.now());

        Assertions.assertEquals(new Price(0.65).value(), basket.price(new Coupons(new ArrayList<Coupon>())).value());
    }

    @Test
    @DisplayName("a basket containing 1 tin of soup, bought today, with null coupons, costs 0.65")
    void test_calculatePrice_soup_null_coupons() {

        Basket basket = new Basket(new BasketEntries(Collections.singletonList(
                new BasketEntry(StockItem.SOUP, 1)
        )), LocalDate.now());

        Assertions.assertEquals(new Price(0.65).value(), basket.price(new Coupons(null)).value());
    }

    @Test
    @DisplayName("a basket containing 7 tins of soup (unmerged), one loaf bread, with/out coupon")
    void test_calculatePrice_multiple_merged_with_bread() {

        Basket basket = new Basket(new BasketEntries(Arrays.asList(
                new BasketEntry(StockItem.SOUP, 1),
                new BasketEntry(StockItem.SOUP, 6),
                new BasketEntry(StockItem.BREAD, 1)
        )), LocalDate.now());

        Assertions.assertEquals(new Price(4.95).value(), basket.price(new Coupons(Collections.singletonList(
                createBreadCoupon()))).value());

        Assertions.assertEquals(new Price(5.35).value(), basket.price(new Coupons(null)).value());
    }

    @Test
    @DisplayName("represent a basket as a string")
    void test_toString() {

        Basket basket = new Basket(new BasketEntries(Arrays.asList(
                new BasketEntry(StockItem.SOUP, 1),
                new BasketEntry(StockItem.SOUP, 6),
                new BasketEntry(StockItem.BREAD, 1)
        )), LocalDate.MIN);

        Assertions.assertEquals("items: 7 SOUP, 1 BREAD, purchase date: -999999999-01-01", basket.stringValue());
    }

    private Coupon createBreadCoupon() {
        return CouponFactory.createBreadCoupon(
                LocalDate.now().minusDays(1),
                LocalDate.now().minusDays(1).plusDays(7));
    }

    private Coupon createApplesCoupon() {
        return CouponFactory.createApplesCoupon(
                LocalDate.now().plusDays(3),
                LocalDate.now().plusDays(3).plusMonths(1).with(TemporalAdjusters.lastDayOfMonth()));
    }
    
}
