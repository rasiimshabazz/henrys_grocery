package com.henrys.kiosk;

abstract class Screen {

    abstract String readResponse();

    abstract void promptUser(final String prompt);

}
