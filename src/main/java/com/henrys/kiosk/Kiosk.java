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
import java.util.function.Function;

public class Kiosk {

    private final Screen screen;

    public Kiosk(Screen screen) {
        this.screen = screen;
    }

    public Basket takeShoppersOrder() {

        screen.printLine(INFO_WELCOME_MESSAGE);

        List<BasketEntry> entries = collectBasketEntries();

        LocalDate purchaseDate = LocalDate.now().plusDays(promptForPurchaseDay());

        Basket basket = new Basket(entries, purchaseDate);

        BigDecimal price = basket.calculatePrice(Coupon.currentPromotion());

        screen.printLine(INFO_TOTAL_PRICE + price + INFO_THANK_YOU);

        return basket;
    }

    private List<BasketEntry> collectBasketEntries() {
        List<BasketEntry> basketEntries = new ArrayList<>();
        boolean isShopping = true;
        while (isShopping) {
            StockItem product = promptForProduct(this::productResponseCondition);
            int quantity = promptForQuantity(product);
            if (quantity > 0) {
                basketEntries.add(new BasketEntry(product, quantity));
            }
            screen.printLine(INFO_BASKET_STATUS_PREFIX + basketEntries);
            isShopping = promptToContinue(this::continueResponseCondition);
        }
        return basketEntries;
    }

    private int promptForQuantity(StockItem product) {
        String prompt = ("how many " + product.getUnit().getPlural() + " of " + product + "? ").toLowerCase();
        return quantityResponse(promptForInput(this::quantityResponseCondition, prompt));
    }

    private int promptForPurchaseDay() {
        return quantityResponse(promptForInput(this::quantityResponseCondition, PROMPT_FOR_DAYS));
    }

    private StockItem promptForProduct(final Function<String, Boolean> function) {
        return productResponse(promptForInput(function, PROMPT_FOR_PRODUCT));
    }

    private Boolean promptToContinue(final Function<String, Boolean> function) {
        return continueResponse(promptForInput(function, PROMPT_FOR_SHOPPING));
    }

    private boolean productResponseCondition(String response) {
        return !StockItem.names().contains(response.toUpperCase());
    }

    private StockItem productResponse(String response) {
        return StockItem.valueOf(response.toUpperCase());
    }

    private boolean continueResponseCondition(String response) {
        return !Arrays.asList(RESPONSE_YES, RESPONSE_NO).contains(response.toLowerCase());
    }
    private boolean continueResponse(String response) {
        return response.equalsIgnoreCase(RESPONSE_YES);
    }

    private boolean quantityResponseCondition(String response) {
        return !isNumeric(response) || !(Integer.parseInt(response) >= 0);
    }

    private int quantityResponse(String response) {
        return Integer.parseInt(response);
    }

    private String promptForInput(Function<String, Boolean> function, String prompt) {
        String response = "";
        while (function.apply(response)) {
            screen.promptUser(prompt);
            response = screen.readResponse().trim();
            if (function.apply(response)) screen.printLine(ERROR_PREFIX + response);
            else break;
        }
        return response;
    }

    private boolean isNumeric(String string) {
        try {
            Integer.valueOf(string);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public static final String INFO_WELCOME_MESSAGE = "\n\n\nwelcome to Henry's! let's price up a basket of shopping.\n";
    public static final String INFO_TOTAL_PRICE = "\ntotal price is: $";
    public static final String INFO_THANK_YOU = "\nthank you! come again.\n\n\n";
    public static final String INFO_BASKET_STATUS_PREFIX = "your entries so far: ";
    public static final String PROMPT_FOR_PRODUCT_PREFIX = "add a product? ";
    public static final String PROMPT_FOR_PRODUCT = PROMPT_FOR_PRODUCT_PREFIX + StockItem.namesToString() + " ";
    public static final String PROMPT_FOR_SHOPPING = "add more? (y/n) ";
    public static final String PROMPT_FOR_DAYS = "bought how many days from now? ";
    public static final String RESPONSE_YES = "y";
    public static final String RESPONSE_NO = "n";
    public static final String ERROR_PREFIX = "! was that a typo?: ";

}
