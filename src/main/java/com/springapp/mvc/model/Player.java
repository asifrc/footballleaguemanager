package com.springapp.mvc.model;

public class Player {
    private final String name;
    private final String number;

    public Player(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
