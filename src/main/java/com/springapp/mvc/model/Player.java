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

    //IntelliJ generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (age != player.age) return false;
        if (name != null ? !name.equals(player.name) : player.name != null) return false;
        if (number != null ? !number.equals(player.number) : player.number != null) return false;
        if (team != null ? !team.equals(player.team) : player.team != null) return false;

        return true;
    }

    //IntelliJ generated
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (team != null ? team.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + age;
        return result;
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
