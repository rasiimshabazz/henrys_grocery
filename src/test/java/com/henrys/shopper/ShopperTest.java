package com.henrys.shopper;

import com.henrys.pricer.Basket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShopperTest {

    private Kiosk kiosk = new Kiosk();
    private Shopper shopper = new Shopper(kiosk);


    @DisplayName("shop")
    void test_goGroceryShopping() {

        Basket expectedBasket = null;
        Basket actualBasket = shopper.goGroceryShopping();

        assertEquals(expectedBasket, actualBasket);
    }

}

