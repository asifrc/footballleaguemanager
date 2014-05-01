package com.springapp.mvc;

import com.springapp.mvc.model.Player;

public class PlayerBuilder {
    private String name = "Default Name";
    private String team = "Default Team";
    private String number = "Default Number";
    private int age = 0;

    public PlayerBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PlayerBuilder withTeam(String team) {
        this.team = team;
        return this;
    }

    public PlayerBuilder withNumber(String number) {
        this.number = number;
        return this;
    }

    public PlayerBuilder withAge(int age) {
        this.age = age;
        return this;
    }

    public Player build() {
        return new Player(name, team, number, age);
    }
}
