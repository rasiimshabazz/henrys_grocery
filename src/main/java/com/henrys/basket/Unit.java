package com.henrys.basket;

public enum Unit {

    LOAF("LOAVES"), BOTTLE("BOTTLES"), SINGLE("SINGLES"), TIN("TINS");

    private final String plural;

    Unit(String plural) {
        this.plural = plural;
    }

    public String getPlural() {
        return plural;
    }
}
