package com.henrys.kiosk;

import com.henrys.pricer.Basket;
import com.henrys.pricer.BasketItem;
import com.henrys.pricer.StockItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kiosk {

    private Screen screen;

    public Kiosk(Screen screen) {
        this.screen = screen;
    }

    public Basket takeShoppersOrder() {

        List<BasketItem> products = new ArrayList<>();

        boolean isShopping = true;
        while (isShopping) {

            StockItem product = promptForProduct();
            int quantity = promptForQuantity();

            products.add(new BasketItem(product, quantity));

            isShopping = promptToContinue();
        }

        return new Basket(products, LocalDate.now());
    }

    private boolean promptToContinue() {
        boolean isShopping = true;
        String shoppingResponse = "";

        while (!Arrays.asList("y", "n").contains(shoppingResponse.toLowerCase())) {

            screen.promptUser(Screen.PROMPT_SHOPPING);
            shoppingResponse = screen.readResponse().trim();

            if (shoppingResponse.equalsIgnoreCase("n")) {
                isShopping = false;
            }
        }
        return isShopping;
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

    private int promptForQuantity() {

        Integer quantity = null;
        while(quantity == null) {

            screen.promptUser(Screen.PROMPT_QUANTITY);
            String quantityResponse = screen.readResponse();
            try {
                quantity = Integer.valueOf(quantityResponse);
            }
            catch (NumberFormatException e) {
                screen.promptUser(Screen.PROMPT_QUANTITY_RETRY);
            }
        }

        return quantity;
    }
}
