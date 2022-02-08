package com.henrys.pricer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShopperTest {

    @Test
    @DisplayName("shop")
    void test_goGroceryShopping() {

        Basket expectedBasket = null;
        Basket actualBasket = new Shopper().goGroceryShopping();

        assertEquals(expectedBasket, actualBasket);
    }

}

