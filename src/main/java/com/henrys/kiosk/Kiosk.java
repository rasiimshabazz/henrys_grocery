package com.henrys.kiosk;

import com.henrys.basket.*;
import com.henrys.coupon.CouponFactory;
import com.henrys.coupon.Coupons;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

public class Kiosk {

    private final Screen screen;

    public Kiosk(Screen screen) {
        this.screen = screen;
    }

    public Basket takeShoppersOrder() {

        screen.printLine(KioskText.INFO_WELCOME_MESSAGE);
        BasketEntries entries = collectBasketEntries();
        Basket basket = new Basket(entries, LocalDate.now().plusDays(promptForPurchaseDay()));
        Price price = basket.price(new Coupons(CouponFactory.createCurrentPromotion()));
        screen.printLine(KioskText.INFO_TOTAL_PRICE + price.value() + KioskText.INFO_THANK_YOU);
        return basket;
    }

    private BasketEntries collectBasketEntries() {
        BasketEntries entries = new BasketEntries(new ArrayList<BasketEntry>());
        boolean isShopping = true;
        while (isShopping) {
            StockItem product = promptForProduct(this::productResponseCondition);
            int quantity = promptForQuantity(product);
            if (quantity > 0) {
                entries.add(new BasketEntry(product, quantity));
            }
            screen.printLine(KioskText.INFO_BASKET_STATUS_PREFIX + entries.stringValue());
            isShopping = promptToContinue(this::continueResponseCondition);
        }
        return entries;
    }

    private int promptForQuantity(StockItem product) {
        String prompt = ("how many " + product.getUnit().getPlural() + " of " + product + "? ").toLowerCase();
        return quantityResponse(promptForInput(this::quantityResponseCondition, prompt));
    }

    private int promptForPurchaseDay() {
        return quantityResponse(promptForInput(this::quantityResponseCondition, KioskText.PROMPT_FOR_DAYS));
    }

    private StockItem promptForProduct(final Function<String, Boolean> function) {
        return productResponse(promptForInput(function, KioskText.PROMPT_FOR_PRODUCT));
    }

    private Boolean promptToContinue(final Function<String, Boolean> function) {
        return continueResponse(promptForInput(function, KioskText.PROMPT_FOR_SHOPPING));
    }

    private boolean productResponseCondition(String response) {
        return !StockItem.names().contains(response.toUpperCase());
    }

    private StockItem productResponse(String response) {
        return StockItem.valueOf(response.toUpperCase());
    }

    private boolean continueResponseCondition(String response) {
        return !Arrays.asList(KioskText.RESPONSE_YES, KioskText.RESPONSE_NO).contains(response.toLowerCase());
    }
    private boolean continueResponse(String response) {
        return response.equalsIgnoreCase(KioskText.RESPONSE_YES);
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
            if (function.apply(response)) screen.printLine(KioskText.ERROR_PREFIX + response);
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

}
