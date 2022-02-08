package com.henrys.kiosk;

public class TestScreen extends Screen {

    public void promptUser(final String prompt) {
        System.out.print(prompt);
    }

    public String readResponse() {
        String response = "testing user response";
        return response;
    }

}
