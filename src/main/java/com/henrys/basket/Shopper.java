package com.henrys.basket;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class Shopper {

    Basket goGroceryShopping() {

        return new Basket(new ArrayList<>(), LocalDate.now());
    }


    public static void main(String[] args) {

        System.out.print("wanna do some shopping? (y/n): ");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();


    }

}
