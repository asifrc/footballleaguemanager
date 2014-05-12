package com.springapp.mvc.integration;

import com.springapp.mvc.controller.TradeController;
import com.springapp.mvc.model.Player;
import com.springapp.mvc.model.PlayerBuilder;
import com.springapp.mvc.service.PlayerService;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TradePlayerTest {
    @Test
    public void shouldPassPlayerListToView() throws Exception {
        PlayerService playerService = new PlayerService();
        TradeController tradeController = new TradeController(playerService);
        List<Player> playerList = new ArrayList<Player>();
        playerList.add(new PlayerBuilder().build());

        playerService.setPlayerList(playerList);

        assertEquals(playerList, (List<Player>) tradeController.showTradePlayers(new ModelMap()).getModelMap().get("playerList"));
    }
}
