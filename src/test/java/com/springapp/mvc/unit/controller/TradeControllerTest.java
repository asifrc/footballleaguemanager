package com.springapp.mvc.unit.controller;

import com.springapp.mvc.controller.TradeController;
import com.springapp.mvc.service.PlayerService;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class TradeControllerTest {
    @Test
    public void shouldDisplayTradePlayerView() throws Exception {
        assertEquals("tradePlayers", new TradeController(mock(PlayerService.class)).showTradePlayers(new ModelMap()).getViewName());
    }
}
