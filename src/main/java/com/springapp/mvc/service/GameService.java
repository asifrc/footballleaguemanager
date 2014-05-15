package com.springapp.mvc.service;

import com.springapp.mvc.model.Game;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GameService {
    List<Game> games;

    public GameService() {
        this.games = new ArrayList<Game>();
    }

    public String getWinLossTieRecordFor(String team) {
        Integer winCount = 0;
        int loseCount = 0;
        int tieCount = 0;


        Map<String, IncrementCommand> someMap = new HashMap<String, IncrementCommand>();
        someMap.put("W", new IncrementCommand(winCount));
        someMap.put("L", new IncrementCommand(loseCount));
        someMap.put("T", new IncrementCommand(tieCount));


        for (Game game : games) {
            someMap.get(game.resultFor(team)).execute();
        }

        return winCount + "-" + loseCount + "-" + tieCount;
    }

    public void addGame(Game game) {
        games.add(game);
    }
}
