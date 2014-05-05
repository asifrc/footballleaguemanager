package com.springapp.mvc.model;

public class Coach {

    private final String name;
    private final String team;
    private final String title;

    public Coach(String name, String team, String title) {
        this.name = name;
        this.team = team;
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public String getTitle() {
        return title;
    }
}
