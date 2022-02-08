package com.henrys.kiosk;

public class TestScreen extends Screen {

    String response;

    public void promptUser(String prompt) {

        if (prompt.equalsIgnoreCase("soup, bread, apples, or milk? ")) {
            response = "bread";
            return;
        }

        if (prompt.equalsIgnoreCase("how many? ")) {
            response = "1";
            return;
        }

        throw new RuntimeException("test not prepared for the prompt: [ " + prompt + " ]");
    }

    public String readResponse() {
        return response;
    }

}
