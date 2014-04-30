package com.springapp.mvc.service;

import com.springapp.mvc.model.Player;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerService {

    private final ArrayList<Player> playerList;

    public PlayerService() {
        this.playerList = new ArrayList<Player>();
    }

    public PlayerService(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public Player findPlayer(String name, String number) {
        Player result = null;
        for (Player player : playerList) {
            if (player.getName().equals(name) && player.getNumber().equals(number)) {
                result = player;
            }
        }
        return result;
    }
}
