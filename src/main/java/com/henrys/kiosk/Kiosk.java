package com.henrys.kiosk;

import com.henrys.basket.Basket;
import com.henrys.basket.BasketEntry;
import com.henrys.coupon.Coupon;
import com.henrys.basket.StockItem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kiosk {

    private final Screen screen;

    public Kiosk(Screen screen) {
        this.screen = screen;
    }

    public Basket takeShoppersOrder() {

        screen.printLine(INFO_WELCOME_MESSAGE);

        List<BasketEntry> basketEntries = collectBasketEntries();
        LocalDate purchaseDate = LocalDate.now().plusDays(promptForPurchaseTiming());

        Basket basket = new Basket(basketEntries, purchaseDate);
        BigDecimal price = basket.calculatePrice(Coupon.currentPromotion());

        screen.printLine(INFO_TOTAL_PRICE + price + INFO_THANK_YOU);

        return basket;
    }

    private List<BasketEntry> collectBasketEntries() {
        List<BasketEntry> basketEntries = new ArrayList<>();
        boolean isShopping = true;
        while (isShopping) {

            StockItem product = promptForProduct();

            int quantity = promptForQuantity(product);

            basketEntries.add(new BasketEntry(product, quantity));

            screen.printLine(INFO_BASKET_STATUS_PREFIX + basketEntries);

            isShopping = promptToContinue();
        }
        return basketEntries;
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

    private int promptForQuantity(StockItem product) {
        Integer quantity = null;
        while(quantity == null || quantity < 1) {
            screen.promptUser(("how many " + product.getUnit().getPlural() + " of " + product + "? ").toLowerCase());
            String quantityResponse = screen.readResponse().trim();
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

    private int promptForPurchaseTiming() {
        Integer numberOfDays = null;
        while(numberOfDays == null || numberOfDays < 0) {
            screen.promptUser("bought how many days from now? ");
            String quantityResponse = screen.readResponse().trim();
            try {
                numberOfDays = Integer.valueOf(quantityResponse);
            }
            catch (NumberFormatException e) {
                screen.printLine(ERROR_PREFIX + quantityResponse);
            }
        }
        return numberOfDays;
    }

    public static final String INFO_WELCOME_MESSAGE = "\n\n\nwelcome to Henry's! let's price up a basket of shopping.\n";
    public static final String INFO_TOTAL_PRICE = "\ntotal price is: $";
    public static final String INFO_THANK_YOU = "\nthank you! come again.\n\n\n";
    public static final String INFO_BASKET_STATUS_PREFIX = "your basket so far: ";

    public static final String PROMPT_FOR_PRODUCT_PREFIX = "add a product? ";
    public static final String PROMPT_FOR_SHOPPING = "add more? (y/n) ";
    public static final String PROMPT_FOR_DAYS = "bought how many days from now? ";

    public static final String RESPONSE_YES = "y";
    public static final String RESPONSE_NO = "n";

    public static final String ERROR_PREFIX = "! you typed: ";

}
