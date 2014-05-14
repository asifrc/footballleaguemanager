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

    public void addGame(Game game) {
        games.add(game);
    }
}
