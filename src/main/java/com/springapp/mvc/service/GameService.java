package com.springapp.mvc.service;

import com.springapp.mvc.model.Game;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameService {
    List<Game> games;

    public GameService() {
        this.games = new ArrayList<Game>();
        String team0 = "Dallas Cowboys";
        String team1 = "Loser";
        int team0score = 50;
        int team1score = 10;
        games.add(new Game(team0, team0score, team1, team1score));
        team0 = "Team0";
        team1 = "Team1";
        team0score = 0;
        team1score = 12;
        games.add(new Game(team0, team0score, team1, team1score));

    }

    public String getWinLossTieRecordFor(String team) {
        int winCount = 0;
        int loseCount = 0;
        int tieCount = 0;

        for (Game game : games) {
            String result = game.resultFor(team);
            if (result.equals("W")) {
                winCount++;
            } else if (result.equals("L")) {
                loseCount++;
            } else if (result.equals("T")) {
                tieCount++;
            }
        }

        return winCount + "-" + loseCount + "-" + tieCount;
    }
}
