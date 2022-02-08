package com.henrys.kiosk;

import java.util.Scanner;

public class CliScreen extends Screen {

    private Scanner scanner = new Scanner(System.in);

    public void promptUser(final String prompt) {
        System.out.print(prompt);
    }

    public String readResponse() {
        String response = this.scanner.nextLine();
        return response;
    }
}
