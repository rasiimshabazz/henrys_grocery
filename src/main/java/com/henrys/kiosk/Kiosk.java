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

        StockItem product = promptForProduct();

        int quantity = promptForQuantity();

        BasketItem basketItem = new BasketItem(product, quantity);
        List<BasketItem> basketItems = Arrays.asList(basketItem);

        return new Basket(basketItems, LocalDate.now());
    }

    private int promptForQuantity() {

        int quantity = 0;
        boolean validNumber = false;
        while(!validNumber) {
            screen.promptUser(Screen.PROMPT_QUANTITY);
            String quantityResponse = screen.readResponse();

            try {
                quantity = Integer.valueOf(quantityResponse);
                validNumber = true;
            }
            catch (NumberFormatException e) {
                screen.promptUser("please enter a number for quantity: ");
            }
        }

        return quantity;
    }

    private StockItem promptForProduct() {
        StockItem product = null;
        while(product == null) {
            List<String> productNames = StockItem.names();
            screen.promptUser(Screen.PROMPT_PRODUCT);
            String productResponse = screen.readResponse().trim().toUpperCase();
            if (productNames.contains(productResponse)) {
                product = StockItem.valueOf(productResponse);
            }
        }
        return product;
    }

}
