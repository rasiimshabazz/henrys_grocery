package com.henrys;

import com.henrys.kiosk.Screen;
import com.henrys.kiosk.Kiosk;

public class Henry {

    public static void main(String[] args) {

        new Kiosk(new Screen()).takeShoppersOrder();

    }
}
