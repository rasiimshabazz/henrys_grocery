package com.henrys.kiosk;

public class TestScreen extends Screen {

    String response;

    public void promptUser(String prompt) {
        System.out.print(prompt);


        if (prompt.equalsIgnoreCase("soup, bread, apples, or milk? ")) {
            response = "bread"; return;
        }

        if (prompt.equalsIgnoreCase("how many? ")) {
            response = "1"; return;
        }

        response = "";
    }

    public String readResponse() {
        return response;
    }

}
