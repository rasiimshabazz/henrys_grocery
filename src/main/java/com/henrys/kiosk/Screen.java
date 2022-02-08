package com.henrys.kiosk;

abstract class Screen {

    public static final String PROMPT_PRODUCT = "add a product to the basket? (soup, bread, apples, milk) ";
    public static final String PROMPT_QUANTITY = "how many of them? ";
    public static final String PROMPT_QUANTITY_RETRY = "please enter a number for quantity: ";
    public static final String PROMPT_SHOPPING = "continue shopping? (y/n) ";

    abstract void promptUser(final String prompt);
    abstract String readResponse();

}
