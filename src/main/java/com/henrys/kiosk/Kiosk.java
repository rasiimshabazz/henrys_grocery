package com.henrys.kiosk;

import com.henrys.pricer.Basket;

public class Kiosk {

    private Screen screen;

    public Kiosk(Screen screen) {
        this.screen = screen;
    }

    public Basket createBasket() {

        screen.promptUser("wanna do some shopping? (y/n): ");

        String response = screen.readResponse();

        if (response.equalsIgnoreCase("y")) {
            System.out.println("ok, let's shop");
        }
        else {
            System.out.println(response + "? ok, next time then.");
        }


        return null;
    }

}
