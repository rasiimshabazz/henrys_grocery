package com.henrys;

import com.henrys.kiosk.CliScreen;
import com.henrys.pricer.Pricer;
import com.henrys.pricer.Coupon;
import com.henrys.kiosk.Kiosk;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {

    public static void  main(String[] args) {

        Kiosk kiosk = new Kiosk(new CliScreen());

        Pricer pricer = new Pricer();
        ArrayList<Coupon> coupons = new ArrayList<>();

        BigDecimal price = pricer.priceBasket(kiosk.createBasket(), coupons);

        System.out.println("price is: " + price);
    }

}
