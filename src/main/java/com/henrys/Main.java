package com.henrys;

import com.henrys.kiosk.Screen;
import com.henrys.pricer.Pricer;
import com.henrys.pricer.Coupon;
import com.henrys.kiosk.Kiosk;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {

    public static void  main(String[] args) {

        Kiosk kiosk = new Kiosk(new Screen());

        ArrayList<Coupon> coupons = new ArrayList<>();

        Pricer pricer = new Pricer();
        BigDecimal price = pricer.priceBasket(kiosk.takeShoppersOrder(), coupons);

        System.out.println("price is: " + price);
    }

}
