package com.henrys.kiosk;

import com.henrys.basket.Basket;
import com.henrys.basket.BasketEntry;
import com.henrys.basket.Coupon;
import com.henrys.basket.StockItem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kiosk {

    private final Screen screen;

    public Kiosk(Screen screen) {
        this.screen = screen;
    }

    public Basket takeShoppersOrder() {

        printIntro();


        List<BasketEntry> basketEntries = new ArrayList<>();
        boolean isShopping = true;
        while (isShopping) {

            StockItem product = promptForProduct();

            int quantity = promptForQuantity();

            basketEntries.add(new BasketEntry(product, quantity));

            screen.printLine(INFO_BASKET_STATUS_PREFIX + basketEntries);

            isShopping = promptToContinue();
        }


        Basket basket = new Basket(basketEntries, LocalDate.now());
        BigDecimal price = basket.calculatePrice(coupons());

        printOutro(price);

        return basket;
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

    private static void printIntro() {
        System.out.println("\n\n\nwelcome to Henry's! let's price up a basket of shopping.\n");
    }

    private static void printOutro(BigDecimal price) {
        System.out.println("\ntotal price is: $" + price);
        System.out.println("\nthank you! come again.\n\n\n");
    }

    private static List<Coupon> coupons() {
        return Arrays.asList(
                Coupon.createBreadCoupon(
                        LocalDate.now().minusDays(1),
                        LocalDate.now().minusDays(1).plusDays(7)),
                Coupon.createApplesCoupon(
                        LocalDate.now().plusDays(3),
                        LocalDate.now().plusDays(3).plusMonths(1).with(TemporalAdjusters.lastDayOfMonth())));
    }

    private static final String PROMPT_FOR_PRODUCT_PREFIX = "add a product? ";
    private static final String PROMPT_FOR_QUANTITY = "how many? ";
    private static final String PROMPT_FOR_SHOPPING = "add more? (y/n) ";
    private static final String RESPONSE_YES = "y";
    private static final String RESPONSE_NO = "n";
    private static final String INFO_BASKET_STATUS_PREFIX = "your basket so far: ";
    private static final String ERROR_PREFIX = "! you typed: ";

}
