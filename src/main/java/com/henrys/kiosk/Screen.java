package com.henrys.kiosk;

import java.util.Scanner;

public class Screen {

    public static final String PROMPT_PRODUCT = "add a product to the basket? (soup, bread, apples, milk) ";
    public static final String PROMPT_QUANTITY = "how many of them? ";
    public static final String PROMPT_QUANTITY_RETRY = "please enter a number for quantity: ";
    public static final String PROMPT_SHOPPING = "continue shopping? (y/n) ";

    private Scanner scanner = new Scanner(System.in);

    public void promptUser(final String prompt) {
        System.out.print(prompt);
    }

    public String readResponse() {
        return this.scanner.nextLine();
    }

}
