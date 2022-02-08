package com.henrys.shopper;

public class TestScreen extends Screen {

    public String readUserInput() {
        String response = "testing user response";
        return response;
    }

    public void promptUser(final String prompt) {
        System.out.print(prompt);
    }
}
