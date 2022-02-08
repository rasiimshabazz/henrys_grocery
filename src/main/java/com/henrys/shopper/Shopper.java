package com.henrys.shopper;

import com.henrys.pricer.Basket;

public class Shopper {

    private Kiosk kiosk;

    public Shopper(Kiosk kiosk) {
        this.kiosk = kiosk;
    }

    public Basket goGroceryShopping() {

        return promptUserAndAddProducts();
    }

    private Basket promptUserAndAddProducts() {

        kiosk.promptUser("wanna do some shopping? (y/n): ");

        String response = kiosk.readUserInput();

        if (response.equalsIgnoreCase("y")) {
            System.out.println("ok, let's shop");
        }
        else {
            System.out.println(response + "? ok, next time then.");
        }


        return null;
    }

}
