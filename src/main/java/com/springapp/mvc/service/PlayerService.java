package com.springapp.mvc.service;

import com.springapp.mvc.model.Player;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class PlayerService {

    private Set<Player> players;

    public PlayerService() {
        this.players = new HashSet<Player>();
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public Set<Player> getPlayersWithMinimumAge(int age) {
        Set<Player> filteredPlayers = new HashSet<Player>();
        for (Player player : players) {
            if (player.getAge() >= age) {
                filteredPlayers.add(player);
            }
        }
        return filteredPlayers;
    }

    public Player findPlayerByName(String name) {
        Player result = null;
        for (Player player : players) {
            if (player.getName().equals(name)) {
                result = player;
            }
        }
        return result;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public Set<Player> getPlayersFrom(String teamName) {
        Set<Player> filteredPlayers = new HashSet<Player>();
        for (Player player : players) {
            if (player.getTeam().equals(teamName)) {
                filteredPlayers.add(player);
            }
        }
        return filteredPlayers;
    }
}
