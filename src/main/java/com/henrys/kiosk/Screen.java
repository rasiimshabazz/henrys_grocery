package com.henrys.kiosk;

import java.util.Scanner;

public class Screen {

    private Scanner scanner = new Scanner(System.in);

    public void promptUser(final String prompt) {
        System.out.print(prompt);
    }

    public String readResponse() {
        return this.scanner.nextLine();
    }

}
