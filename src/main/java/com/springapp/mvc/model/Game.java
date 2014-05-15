package com.springapp.mvc.model;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class Game {

    private final LinkedHashMap<String, String> results = new LinkedHashMap<String, String>();
    private String team0;
    private int team0score;
    private String team1;
    private int team1score;

    public Game() {
        team1score = 0;
        team1 = "";
        team0score = 0;
        team0 = "";
        createResults();
    }

    public Game(String team0, int team0score, String team1, int team1score) {
        this.team0 = team0;
        this.team0score = team0score;
        this.team1 = team1;
        this.team1score = team1score;
        createResults();
    }

    private void createResults() {
        results.clear();
        results.put(this.team0, determineWinLossTie(this.team0score, this.team1score));
        results.put(this.team1, determineWinLossTie(this.team1score, this.team0score));
    }

    private String determineWinLossTie(int score0, int score1) {
        if (score0 > score1) {
            return "W";
        } else if (score0 < score1) {
            return "L";
        } else {
            return "T";
        }
    }

    public String getTeam(int teamNumber) {
        createResults();
        return (String) results.keySet().toArray()[teamNumber];
    }

    public String resultFor(String team) {
        createResults();
        String result = results.get(team);

        if (result == null) {
            return "N.A.";
        } else {
            return result;
        }
    }

    public String getTeam0() {
        return team0;
    }

    public int getTeam0score() {
        return team0score;
    }

    public String getTeam1() {
        return team1;
    }

    public int getTeam1score() {
        return team1score;
    }

    public void setTeam0(String team0) {
        this.team0 = team0;
    }

    public void setTeam0score(int team0score) {
        this.team0score = team0score;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public void setTeam1score(int team1score) {
        this.team1score = team1score;
    }

    public String displayResults() {
        String result = getTeam(0) + ": " + resultFor(getTeam(0)) + ", ";
        result += getTeam(1) + ": " + resultFor(getTeam(1));
        return result;
    }
}
