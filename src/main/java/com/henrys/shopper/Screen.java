package com.henrys.shopper;

import java.util.Scanner;

public class Screen {

    private Scanner scanner = new Scanner(System.in);

    public Screen() {
    }

    public String readUserInput() {
        String response = this.scanner.nextLine();
        return response;
    }

    public void promptUser(final String prompt) {
        System.out.print(prompt);
    }

}
