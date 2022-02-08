package com.henrys;

import java.time.LocalDate;
import java.util.ArrayList;

public class Shopper {


    public Basket goGroceryShopping() {

        return new Basket(new ArrayList<>(), LocalDate.now());
    }
}
