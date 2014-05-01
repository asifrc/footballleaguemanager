package com.springapp.mvc.model;

import org.springframework.stereotype.Component;

@Component
public class Player {
    private String name;
    private String team;
    private String number;
    private int age;

    public Player() {
    }

    public Player(String name, String team, String number, int age) {
        this.name = name;
        this.team = team;
        this.number = number;
        this.age = age;
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

    public String getTeam() {
        return team;
    }

    public Player setTeam(String team) {
        this.team = team;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Player setAge(String age) {
        this.age = Integer.parseInt(age);
        return this;
    }
}
