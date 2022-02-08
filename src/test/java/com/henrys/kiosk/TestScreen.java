package com.henrys.kiosk;

public class TestScreen extends Screen {

    private String prompt;

    public void promptUser(final String prompt) {
        System.out.print(prompt);
        this.prompt = prompt;
    }

    public String readResponse() {
        String response = "<test user response>";
        return response;
    }

}
