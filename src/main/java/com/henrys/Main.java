package com.henrys;

import com.henrys.pricer.Cashier;
import com.henrys.pricer.Coupon;
import com.henrys.pricer.Shopper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void  main(String[] args) {

        Shopper shopper = new Shopper();

        Scanner scanner = new Scanner(System.in);
        String prompt = "wanna do some shopping? (y/n): ";
        System.out.print(prompt);
        String response = scanner.nextLine();

        Cashier cashier = new Cashier();
        ArrayList<Coupon> coupons = new ArrayList<>();
        BigDecimal price = cashier.priceBasket(shopper.goGroceryShopping(), coupons);
    }

}
