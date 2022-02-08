package com.henrys.kiosk;

import com.henrys.pricer.Basket;
import com.henrys.pricer.BasketItem;
import com.henrys.pricer.StockItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class KioskTest {

    private CliScreen mockScreen;
    private Kiosk kiosk;

    @BeforeEach
    void setup() {
        mockScreen = mock(CliScreen.class);
        kiosk = new Kiosk(mockScreen);
    }

    @Test
    @DisplayName("when user enters 'bread', '1' - basket should have 1 bread")
    void test_createBasket() {

        List<BasketItem> bread = Arrays.asList(new BasketItem(StockItem.BREAD, 1));
        given(mockScreen.readResponse()).willReturn("bread").willReturn("1");
        assertEquals(new Basket(bread, LocalDate.now()).toString(), kiosk.createBasket().toString());
    }


    @Test
    @DisplayName("when user enters 'soup', '1' - basket should have 1 soup")
    void test_createBasket_1_soup() {

        List<BasketItem> expectedProducts = Arrays.asList(new BasketItem(StockItem.SOUP, 1));
        given(mockScreen.readResponse()).willReturn("soup").willReturn("1");
        assertEquals(new Basket(expectedProducts, LocalDate.now()).toString(), kiosk.createBasket().toString());
    }

    @Test
    @DisplayName("when user enters ' ', 'soup', '1' - basket should have 1 soup")
    void test_createBasket_1_soup_with_blank() {

        List<BasketItem> expectedProducts = Arrays.asList(new BasketItem(StockItem.SOUP, 1));

        given(mockScreen.readResponse())
                .willReturn(" ")
                .willReturn("soup")
                .willReturn("1");

        assertEquals(new Basket(expectedProducts, LocalDate.now()).toString(), kiosk.createBasket().toString());
    }

    @Test
    @DisplayName("when user enters ' ', 'soup', ' ', '1' - basket should have 1 soup")
    void test_createBasket_1_soup_with_blank_entries() {

        List<BasketItem> expectedProducts = Arrays.asList(new BasketItem(StockItem.SOUP, 1));

        given(mockScreen.readResponse())
                .willReturn(" ")
                .willReturn(" ")
                .willReturn("soup")
                .willReturn(" ")
                .willReturn(" ")
                .willReturn("1");

        assertEquals(new Basket(expectedProducts, LocalDate.now()).toString(), kiosk.createBasket().toString());
    }

}

