package com.springapp.mvc.controller;

import com.springapp.mvc.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TradeController {

    private final PlayerService playerService;

    @Autowired
    public TradeController(PlayerService playerService) {

        this.playerService = playerService;
    }

    @RequestMapping("/trade")
    public ModelAndView showTradePlayers(ModelMap modelMap) {
        modelMap.addAttribute("playerList", playerService.getPlayerList());
        return new ModelAndView("tradePlayers", modelMap);
    }
}
