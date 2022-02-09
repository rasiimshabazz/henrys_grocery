package com.henrys.kiosk;

import com.henrys.pricer.Basket;
import com.henrys.pricer.BasketEntry;
import com.henrys.pricer.StockItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kiosk {

    public static final String PROMPT_FOR_PRODUCT_PREFIX = "add a product? ";
    public static final String PROMPT_FOR_QUANTITY = "how many? ";
    public static final String PROMPT_FOR_SHOPPING = "add more? (y/n) ";
    public static final String RESPONSE_YES = "y";
    public static final String RESPONSE_NO = "n";
    public static final String INFO_BASKET_PREFIX = "your basket so far: ";
    public static final String ERROR_PREFIX = "! you typed: ";

    private final Screen screen;

    public Kiosk(Screen screen) {
        this.screen = screen;
    }

    public Basket takeShoppersOrder() {

        List<BasketEntry> basketEntries = new ArrayList<>();
        boolean isShopping = true;
        while (isShopping) {

            StockItem product = promptForProduct();
            int quantity = promptForQuantity();

            basketEntries.add(new BasketEntry(product, quantity));

            screen.printLine(INFO_BASKET_PREFIX + basketEntries.toString());
            isShopping = promptToContinue();
        }

        return new Basket(basketEntries, LocalDate.now());
    }

    private StockItem promptForProduct() {

        StockItem product = null;
        while(product == null) {

            screen.promptUser(PROMPT_FOR_PRODUCT_PREFIX + StockItem.namesToString() + " ");
            String productResponse = screen.readResponse();
            String productResponseUpperCase = productResponse.trim().toUpperCase();
            if (StockItem.names().contains(productResponseUpperCase)) {
                product = StockItem.valueOf(productResponseUpperCase);
            }
            else {
                screen.printLine(ERROR_PREFIX + productResponse);
            }
        }

        return product;
    }

    private int promptForQuantity() {

        Integer quantity = null;
        while(quantity == null) {

            screen.promptUser(PROMPT_FOR_QUANTITY);
            String quantityResponse = screen.readResponse();
            try {
                quantity = Integer.valueOf(quantityResponse);
            }
            catch (NumberFormatException e) {
                screen.printLine(ERROR_PREFIX + quantityResponse);
             }
        }

        return quantity;
    }

    private boolean promptToContinue() {

        boolean isShopping = true;
        String shoppingResponse = "";
        while (!Arrays.asList(RESPONSE_YES, RESPONSE_NO).contains(shoppingResponse.toLowerCase())) {

            screen.promptUser(PROMPT_FOR_SHOPPING);
            shoppingResponse = screen.readResponse().trim();

            if (shoppingResponse.equalsIgnoreCase(RESPONSE_NO)) {
                isShopping = false;
            }
        }
        return isShopping;
    }

}
