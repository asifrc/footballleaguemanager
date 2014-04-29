package com.springapp.mvc.service;

import com.springapp.mvc.model.Player;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerService {

    public List<Player> getPlayers() {
        List<Player> playerList = new ArrayList<Player>();
        playerList.add(new Player("Bob"));
        playerList.add(new Player("Sally"));
        return playerList;
    }
}
