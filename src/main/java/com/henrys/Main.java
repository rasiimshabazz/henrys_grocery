package com.henrys;

import com.henrys.shopper.CliScreen;
import com.henrys.pricer.Cashier;
import com.henrys.pricer.Coupon;
import com.henrys.shopper.Kiosk;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {

    public static void  main(String[] args) {

        Kiosk kiosk = new Kiosk(new CliScreen());


        Cashier cashier = new Cashier();
        ArrayList<Coupon> coupons = new ArrayList<>();
        BigDecimal price = cashier.priceBasket(kiosk.goGroceryShopping(), coupons);
    }

}
