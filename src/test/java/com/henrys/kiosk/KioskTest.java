package com.henrys.kiosk;

import com.henrys.pricer.Basket;
import com.henrys.pricer.BasketItem;
import com.henrys.pricer.StockItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KioskTest {

    private Screen screen = new TestScreen();
    private Kiosk kiosk = new Kiosk(screen);

    @Test
    @DisplayName("user enters: soup, 1")
    void test_createBasket() {

        List<BasketItem> basketItems = Arrays.asList(new BasketItem(StockItem.BREAD, 1));
        Basket expectedBasket = new Basket(basketItems, LocalDate.now());

        Basket actualBasket = kiosk.createBasket();

        assertEquals(expectedBasket.toString(), actualBasket.toString());
    }

}

