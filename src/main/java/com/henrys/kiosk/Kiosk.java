package com.henrys.kiosk;

import com.henrys.pricer.Basket;
import com.henrys.pricer.BasketItem;
import com.henrys.pricer.StockItem;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Kiosk {

    private Screen screen;

    public Kiosk(Screen screen) {
        this.screen = screen;
    }

    public Basket createBasket() {

        screen.promptUser("wanna do some shopping? (y/n): ");

        String response = screen.readResponse();

        if (response.equalsIgnoreCase("y")) {
            System.out.println("ok, let's shop");
        }
        else {
            System.out.println(response + "? ok, next time then.");
        }

        List<BasketItem> basketItems = Arrays.asList(new BasketItem(StockItem.BREAD, 1));
        return new Basket(basketItems, LocalDate.now());
    }

}
