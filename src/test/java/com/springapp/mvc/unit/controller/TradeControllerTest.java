package com.springapp.mvc.unit.controller;

import com.springapp.mvc.controller.TradeController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TradeControllerTest {
    @Test
    public void shouldDisplayTradePlayerView() {
        assertEquals("tradePlayers", new TradeController().showTradePlayers());
    }
}
