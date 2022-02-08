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

    @Test
    @DisplayName("when user enters 'bread', '1' - basket should have 1 bread")
    void test_createBasket() {
        Kiosk kiosk = new Kiosk(new TestScreen());
        List<BasketItem> bread = Arrays.asList(new BasketItem(StockItem.BREAD, 1));
        assertEquals(new Basket(bread, LocalDate.now()).toString(), kiosk.createBasket().toString());
    }


//    @Test
//    @DisplayName("when user enters 'soup', '1' - basket should have 1 soup")
//    void test_createBasket_1_soup() {
//        Kiosk kiosk = new Kiosk(new TestScreen());
//        List<BasketItem> soup = Arrays.asList(new BasketItem(StockItem.SOUP, 1));
//        assertEquals(new Basket(soup, LocalDate.now()).toString(), kiosk.createBasket().toString());
//    }

}

