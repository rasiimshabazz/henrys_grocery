package com.henrys.kiosk;

import com.henrys.pricer.Basket;
import com.henrys.pricer.BasketItem;
import com.henrys.pricer.StockItem;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Kiosk {

    private Screen screen;

    public Kiosk(Screen screen) {
        this.screen = screen;
    }

    public Basket createBasket() {

        String productPrompt = "soup, bread, apples, or milk? ";
        screen.promptUser(productPrompt);
        String productResponse = screen.readResponse();
        StockItem product = StockItem.valueOf(productResponse.toUpperCase());

        String quantityPrompt = "how many? ";
        screen.promptUser(quantityPrompt);
        String quantityResponse = screen.readResponse();
        int quantity = Integer.valueOf(quantityResponse);

        BasketItem basketItem = new BasketItem(product, quantity);
        List<BasketItem> basketItems = Arrays.asList(basketItem);
        return new Basket(basketItems, LocalDate.now());
    }

}
