package com.henrys.kiosk;

import com.henrys.basket.StockItem;

public class KioskText {

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
