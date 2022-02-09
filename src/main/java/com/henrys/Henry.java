package com.henrys;

import com.henrys.kiosk.Screen;
import com.henrys.pricer.Pricer;
import com.henrys.pricer.Coupon;
import com.henrys.kiosk.Kiosk;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("============================");
        System.out.println("welcome to Henry's! let's price up a basket of shopping.\n");

        Kiosk kiosk = new Kiosk(new Screen());
        BigDecimal price = new Pricer().priceBasket(kiosk.takeShoppersOrder(), createCoupons());

        System.out.println("\ntotal price is: $" + price);
        System.out.println("\nthank you! come again.\n");
        System.out.println("============================");
    }

    private static List<Coupon> createCoupons() {
        return Arrays.asList(
            Coupon.createBreadCoupon(
                    LocalDate.now().minusDays(1),
                    LocalDate.now().minusDays(1).plusDays(7)),
            Coupon.createApplesCoupon(
                    LocalDate.now().plusDays(3),
                    LocalDate.now().plusDays(3).plusMonths(1).with(TemporalAdjusters.lastDayOfMonth())));
    }

}
