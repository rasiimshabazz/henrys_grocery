package com.henrys.kiosk;

import com.henrys.basket.Basket;
import com.henrys.basket.BasketEntry;
import com.henrys.basket.StockItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class KioskTest {

    private Screen mockScreen;
    private Kiosk kiosk;

    @BeforeEach
    void setup() {
        mockScreen = mock(Screen.class);
        kiosk = new Kiosk(mockScreen);
    }

    @Test
    @DisplayName("when user enters 'bread', '1' - basket should have 1 bread")
    void test_takeShoppersOrder() {

        List<BasketEntry> bread = Collections.singletonList(new BasketEntry(StockItem.BREAD, 1));
        given(mockScreen.readResponse())
                .willReturn("bread")
                .willReturn("1")
                .willReturn("n");

        assertEquals(new Basket(bread, LocalDate.now()).toString(), kiosk.takeShoppersOrder().toString());
        then(mockScreen).should(times(3)).readResponse();
    }

    @Test
    @DisplayName("when user enters 1 soup and blank entries - basket should have 1 soup")
    void test_takeShoppersOrder_1_soup_with_blank_entries() {

        List<BasketEntry> expectedProducts = Collections.singletonList(new BasketEntry(StockItem.SOUP, 1));

        given(mockScreen.readResponse())
                .willReturn(" ")
                .willReturn(" ")
                .willReturn("soup")
                .willReturn(" ")
                .willReturn("1")
                .willReturn(" ")
                .willReturn(" ")
                .willReturn("n");

        assertEquals(new Basket(expectedProducts, LocalDate.now()).toString(), kiosk.takeShoppersOrder().toString());
        then(mockScreen).should(times(8)).readResponse();

    }

    @Test
    @DisplayName("when user enters 1 soup, blank entries, 3 bread, blanks again - basket should have 1 soup and 3 breads")
    void test_takeShoppersOrder_1_soup_3_bread_with_blank_entries() {

        List<BasketEntry> expectedProducts = Arrays.asList(
                new BasketEntry(StockItem.SOUP, 1),
                new BasketEntry(StockItem.BREAD, 3));

        given(mockScreen.readResponse())
                .willReturn(" ")
                .willReturn(" ")
                .willReturn("soup")
                .willReturn(" ")
                .willReturn("1")
                .willReturn(" ")
                .willReturn("y")
                .willReturn("bread")
                .willReturn(" ")
                .willReturn("3")
                .willReturn(" ")
                .willReturn("n");

        assertEquals(new Basket(expectedProducts, LocalDate.now()).toString(), kiosk.takeShoppersOrder().toString());
        then(mockScreen).should(times(12)).readResponse();

    }

    @Test
    @DisplayName("when user enters 1 soup, 3 bread, 5 apples, blanks - basket should have 1 soup, 3 breads, 5 apples")
    void test_takeShoppersOrder_1_soup_3_bread_5_apples_with_blanks() {

        List<BasketEntry> expectedProducts = Arrays.asList(
                new BasketEntry(StockItem.SOUP, 1),
                new BasketEntry(StockItem.BREAD, 3),
                new BasketEntry(StockItem.APPLES, 5));

        given(mockScreen.readResponse())
                .willReturn(" ")
                .willReturn("soup")
                .willReturn("1")
                .willReturn("y")
                .willReturn("bread")
                .willReturn("3")
                .willReturn("y")
                .willReturn("apples")
                .willReturn("5")
                .willReturn("n")
                .willReturn(" ");

        assertEquals(new Basket(expectedProducts, LocalDate.now()).toString(), kiosk.takeShoppersOrder().toString());
        then(mockScreen).should(times(10)).readResponse();

    }

}

