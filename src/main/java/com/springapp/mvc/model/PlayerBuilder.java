package com.springapp.mvc.model;

public class PlayerBuilder {
    private String name = "Default Name";
    private String team = "Default Team";
    private String number = "Default Number";
    private int age = 20;

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

    public static Player buildPlayerFrom(String line) {
        String[] playerFields = line.split(",");
        if (playerFields.length > 4) {
            throw new RuntimeException("Too many fields");
        }
        String name = playerFields[0];
        String team = playerFields[1];
        String number = playerFields[2];
        int age = Integer.parseInt(playerFields[3]);

        return new Player(name, team, number, age);
    }
}
