package com.henrys.kiosk;

import com.henrys.pricer.Basket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KioskTest {

    private Screen screen = new TestScreen();
    private Kiosk kiosk = new Kiosk(screen);

    @Test
    @DisplayName("shop")
    void test_createBasket() {

        Basket expectedBasket = null;

        Basket actualBasket = kiosk.createBasket();



        assertEquals(expectedBasket, actualBasket);
    }

}

