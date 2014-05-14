package com.springapp.mvc.service;

import com.springapp.mvc.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TradeService {
    private PlayerService playerService;

    @Autowired
    public TradeService(PlayerService playerService) {
        this.playerService = playerService;
    }

    public void tradePlayers(List<String> names,
                             List<String> currentTeams,
                             List<String> numbers,
                             List<String> ages,
                             List<String> newTeams) {

        for (int i=0; i < names.size(); i++) {
            String name = names.get(i);
            String newTeam = newTeams.get(i);
            Player player = playerService.findPlayerByName(name);
            if (player != null && !newTeam.equals("-- Trade --")) {
                player.setTeam(newTeam);
            }
        }


    }
}
