package com.henrys.kiosk;

import com.henrys.basket.Basket;
import com.henrys.basket.BasketEntry;
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
        List<BasketEntry> basketEntries = new ArrayList<>();
        boolean isShopping = true;
        while (isShopping) {
            final Function<String, Boolean> function1 = response11 -> !StockItem.names().contains(response11.toUpperCase());
            String response2 = "";
            while (function1.apply(response2)) {
                screen.promptUser(PROMPT_FOR_PRODUCT);
                response2 = screen.readResponse().trim();
                if (function1.apply(response2)) screen.printLine(ERROR_PREFIX + response2);
                else break;
            }
            StockItem product = StockItem.valueOf(response2.toUpperCase());
            String prompt = ("how many " + product.getUnit().getPlural() + " of " + product + "? ").toLowerCase();
            Function<String, Boolean> function2 = response11 -> {
                boolean result;
                try {
                    Integer.valueOf(response11);
                    result = true;
                }
                catch (NumberFormatException e) {
                    result = false;
                }
                return !result || !(Integer.parseInt(response11) >= 0);
            };
            String response3 = "";
            while (function2.apply(response3)) {
                screen.promptUser(prompt);
                response3 = screen.readResponse().trim();
                if (function2.apply(response3)) screen.printLine(ERROR_PREFIX + response3);
                else break;
            }
            int quantity = Integer.parseInt(response3);
            if (quantity > 0) {
                basketEntries.add(new BasketEntry(product, quantity));
            }
            screen.printLine(INFO_BASKET_STATUS_PREFIX + basketEntries);
            final Function<String, Boolean> function11 = response21 -> !Arrays.asList(RESPONSE_YES, RESPONSE_NO).contains(response21.toLowerCase());
            String response11 = "";
            while (function11.apply(response11)) {
                screen.promptUser(PROMPT_FOR_SHOPPING);
                response11 = screen.readResponse().trim();
                if (function11.apply(response11)) screen.printLine(ERROR_PREFIX + response11);
                else break;
            }
            isShopping = response11.equalsIgnoreCase(RESPONSE_YES);
        }
        Function<String, Boolean> function = response1 -> {
            boolean result;
            try {
                Integer.valueOf(response1);
                result = true;
            }
            catch (NumberFormatException e) {
                result = false;
            }
            return !result || !(Integer.parseInt(response1) >= 0);
        };
        String response = "";
        while (function.apply(response)) {
            screen.promptUser(PROMPT_FOR_DAYS);
            response = screen.readResponse().trim();
            if (function.apply(response)) screen.printLine(ERROR_PREFIX + response);
            else break;
        }
        LocalDate purchaseDate = LocalDate.now().plusDays(Integer.parseInt(response));
        Basket basket = new Basket(basketEntries, purchaseDate);
        BigDecimal price = basket.calculatePrice(null);
        screen.printLine(INFO_TOTAL_PRICE + price + INFO_THANK_YOU);
        return basket;
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
