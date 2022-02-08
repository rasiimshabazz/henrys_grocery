package com.henrys.kiosk;

public class TestScreen extends Screen {

    String response;

    public void promptUser(String prompt) {

        if (prompt.equalsIgnoreCase(PROMPT_PRODUCT)) {
            response = "bread";
            return;
        }

        if (prompt.equalsIgnoreCase(PROMPT_QUANTITY)) {
            response = "1";
            return;
        }

        throw new RuntimeException("test not prepared for the prompt: [ " + prompt + " ]");
    }

    public String readResponse() {
        return response;
    }

}
