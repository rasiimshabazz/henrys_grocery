package com.henrys.kiosk;

abstract class Screen {

    public static final String PROMPT_PRODUCT = "soup, bread, apples, or milk? ";
    public static final String PROMPT_QUANTITY = "how many? ";

    abstract String readResponse();

    abstract void promptUser(final String prompt);

}
