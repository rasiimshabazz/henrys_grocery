package com.henrys.basket;

import java.time.LocalDate;
import java.util.ArrayList;

class Shopper {

    Basket goGroceryShopping() {

        return new Basket(new ArrayList<>(), LocalDate.now());
    }

}
