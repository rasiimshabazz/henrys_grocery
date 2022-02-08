package com.henrys.shopper;

import com.henrys.pricer.Basket;

import java.util.Scanner;

public class Shopper {

    private Kiosk kiosk;

    public Shopper(Kiosk kiosk) {
        this.kiosk = kiosk;
    }

    public Basket goGroceryShopping() {

        return promptUserAndAddProducts();
    }

    private Basket promptUserAndAddProducts() {

        return null;
    }

    public static String readUserInput(Scanner scanner) {
        String response = scanner.nextLine();
        return response;
    }

    public static void promptUser(final String prompt) {
        System.out.print(prompt);
    }
}
