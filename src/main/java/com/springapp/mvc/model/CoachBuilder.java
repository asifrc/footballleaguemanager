package com.springapp.mvc.model;

public class CoachBuilder {
    private String name = "Default Name";
    private String team = "Default Team";
    private String position = "Default Position";

    public CoachBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CoachBuilder withTeam(String team) {
        this.team = team;
        return this;
    }

    public CoachBuilder withPosition(String position) {
        this.position = position;
        return this;
    }

    public Coach build() {
        return new Coach(name, team, position);
    }
}
