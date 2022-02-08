package com.henrys;

import com.henrys.shopper.Kiosk;
import com.henrys.pricer.Cashier;
import com.henrys.pricer.Coupon;
import com.henrys.shopper.Shopper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void  main(String[] args) {

        Shopper shopper = new Shopper(new Kiosk());

        Scanner scanner = new Scanner(System.in);

        Shopper.promptUser("wanna do some shopping? (y/n): ");

        String response = Shopper.readUserInput(scanner);

        if (response.equalsIgnoreCase("y")) {
            System.out.println("ok, let's shop");
        }
        else {
            System.out.println(response + "? ok, next time then.");
        }

        Cashier cashier = new Cashier();
        ArrayList<Coupon> coupons = new ArrayList<>();
        BigDecimal price = cashier.priceBasket(shopper.goGroceryShopping(), coupons);
    }

}
