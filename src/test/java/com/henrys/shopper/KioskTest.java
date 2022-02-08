package com.henrys.shopper;

import com.henrys.pricer.Basket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KioskTest {

    private Screen screen = new ScreenImpl();
    private Kiosk kiosk = new Kiosk(screen);

    @DisplayName("shop")
    void test_goGroceryShopping() {

        Basket expectedBasket = null;
        Basket actualBasket = kiosk.goGroceryShopping();

        assertEquals(expectedBasket, actualBasket);
    }

}

