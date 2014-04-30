package com.springapp.mvc.model;

import org.springframework.stereotype.Component;

@Component
public class Player {
    private String name;
    private String number;


    public Player() {
    }

    public Player(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public Player setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getName() {
        return name;
    }

    public Player setName(String name) {
        this.name = name;
        return this;
    }
}
