package com.henrys;

import com.henrys.kiosk.Screen;
import com.henrys.pricer.Pricer;
import com.henrys.pricer.Coupon;
import com.henrys.kiosk.Kiosk;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("============================");
        System.out.println("welcome to Henry's! let's price up a basket of shopping.\n");

        Kiosk kiosk = new Kiosk(new Screen());
        ArrayList<Coupon> coupons = new ArrayList<>();
        BigDecimal price = new Pricer().priceBasket(kiosk.takeShoppersOrder(), coupons);
        System.out.println("\ntotal price is: $" + price);

        System.out.println("\nthank you! come again.\n");
        System.out.println("============================");
    }

}
