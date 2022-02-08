package com.henrys.shopper;

import java.util.Scanner;

public class Kiosk {

    private Scanner scanner = new Scanner(System.in);

    public Kiosk() {


    }

    public String readUserInput() {
        String response = this.scanner.nextLine();
        return response;
    }

    public void promptUser(final String prompt) {
        System.out.print(prompt);
    }

}
