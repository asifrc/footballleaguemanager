package com.springapp.mvc.service;

import com.springapp.mvc.model.Player;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerService {

    private List<Player> playerList;

    public PlayerService() {
        this.playerList = new ArrayList<Player>();
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public Player findPlayerByName(String name) {
        Player result = null;
        for (Player player : playerList) {
            if (player.getName().equals(name)) {
                result = player;
            }
        }
        return result;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public List<Player> getPlayersFrom(String teamName) {
        ArrayList<Player> filterPlayers = new ArrayList<Player>();
        for (Player player : playerList) {
            if (player.getTeam().equals(teamName)) {
                filterPlayers.add(player);
            }
        }
        return filterPlayers;
    }
}
