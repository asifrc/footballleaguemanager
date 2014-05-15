package com.springapp.mvc.model;

public class CoachBuilder {
    private String name = "Default Name";
    private String team = "Default Team";
    private String position = "Default Position";

    public static Coach buildCoachFrom(String line) {
        String[] coachFields = line.split(",");
        if (coachFields.length > 3) {
            throw new RuntimeException("Too many fields");
        }

        String name = coachFields[0];
        String team = coachFields[1];
        String title = coachFields[2];

        return new Coach(name, team, title);
    }

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
