package com.henrys;

import com.henrys.kiosk.Screen;
import com.henrys.pricer.Basket;
import com.henrys.pricer.Pricer;
import com.henrys.pricer.Coupon;
import com.henrys.kiosk.Kiosk;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;

public class Henry {

    public static void main(String[] args) {

        printIntro();

        Basket basket = new Kiosk(new Screen()).takeShoppersOrder();

        BigDecimal price = Pricer.priceBasket(basket, coupons());

        printOutro(price);

    }

    private static void printIntro() {
        System.out.println("\n============================");
        System.out.println("welcome to Henry's! let's price up a basket of shopping.\n");
    }

    private static void printOutro(BigDecimal price) {
        System.out.println("\ntotal price is: $" + price);
        System.out.println("\nthank you! come again.");
        System.out.println("\n============================");
    }

    private static List<Coupon> coupons() {
        return Arrays.asList(
            Coupon.createBreadCoupon(
                    LocalDate.now().minusDays(1),
                    LocalDate.now().minusDays(1).plusDays(7)),
            Coupon.createApplesCoupon(
                    LocalDate.now().plusDays(3),
                    LocalDate.now().plusDays(3).plusMonths(1).with(TemporalAdjusters.lastDayOfMonth())));
    }

}
