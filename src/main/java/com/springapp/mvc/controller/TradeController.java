package com.springapp.mvc.controller;

import com.springapp.mvc.service.PlayerService;
import com.springapp.mvc.service.TeamService;
import com.springapp.mvc.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TradeController {

    private final PlayerService playerService;
    private final TeamService teamService;
    private final TradeService tradeService;

    @Autowired
    public TradeController(PlayerService playerService, TeamService teamService, TradeService tradeService) {
        this.playerService = playerService;
        this.teamService = teamService;
        this.tradeService = tradeService;
    }

    @RequestMapping(value="/trade", method= RequestMethod.GET)
    public ModelAndView renderTradePlayersView(ModelMap modelMap) {
        modelMap.addAttribute("players", playerService.getPlayers());
        modelMap.addAttribute("teams", teamService.getTeams());

        return new ModelAndView("tradePlayers", modelMap);
    }

    @RequestMapping(value="/trade", method= RequestMethod.POST)
    public ModelAndView handleTradeRequest(ModelMap model,
                                           @RequestParam("name") List<String> names,
                                           @RequestParam("team") List<String> currentTeams,
                                           @RequestParam("number") List<String> numbers,
                                           @RequestParam("age") List<String> ages,
                                           @RequestParam("new-team") List<String> newTeams) {

        tradeService.tradePlayers(names, currentTeams, numbers, ages, newTeams);
        return new ModelAndView("redirect:/");
    }
}
