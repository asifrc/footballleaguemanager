package com.springapp.mvc.service;

import com.springapp.mvc.model.Player;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class PlayerService {

    private Set<Player> playerList;

    public PlayerService() {
        this.playerList = new HashSet<Player>();
    }

    public Set<Player> getPlayerList() {
        return playerList;
    }

    public Set<Player> getPlayersWithMinimumAge(int age) {
        Set<Player> filteredPlayerList = new HashSet<Player>();
        for (Player player : playerList) {
            if (player.getAge() >= age) {
                filteredPlayerList.add(player);
            }
        }
        return filteredPlayerList;
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

    public void setPlayerList(Set<Player> playerList) {
        this.playerList = playerList;
    }

    public Set<Player> getPlayersFrom(String teamName) {
        Set<Player> filterPlayers = new HashSet<Player>();
        for (Player player : playerList) {
            if (player.getTeam().equals(teamName)) {
                filterPlayers.add(player);
            }
        }
        return filterPlayers;
    }
}
