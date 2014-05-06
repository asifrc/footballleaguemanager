package com.springapp.mvc.model;

public class Coach extends TeamMember {

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

    //IntelliJ generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coach coach = (Coach) o;

        if (name != null ? !name.equals(coach.name) : coach.name != null) return false;
        if (team != null ? !team.equals(coach.team) : coach.team != null) return false;
        if (title != null ? !title.equals(coach.title) : coach.title != null) return false;

        return true;
    }

    //IntelliJ generated
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (team != null ? team.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
