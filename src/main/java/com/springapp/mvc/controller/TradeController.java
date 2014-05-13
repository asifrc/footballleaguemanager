package com.springapp.mvc.controller;

import com.springapp.mvc.service.PlayerService;
import com.springapp.mvc.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TradeController {

    private final PlayerService playerService;
    private final TeamService teamService;

    @Autowired
    public TradeController(PlayerService playerService, TeamService teamService) {
        this.playerService = playerService;
        this.teamService = teamService;
    }

    @RequestMapping("/trade")
    public ModelAndView showTradePlayers(ModelMap modelMap) {
        modelMap.addAttribute("players", playerService.getPlayers());
        modelMap.addAttribute("teams", teamService.getTeams());

        return new ModelAndView("tradePlayers", modelMap);
    }
}
