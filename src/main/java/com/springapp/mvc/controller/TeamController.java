package com.springapp.mvc.controller;

import com.springapp.mvc.service.CoachService;
import com.springapp.mvc.service.GameService;
import com.springapp.mvc.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/team")
public class TeamController {

    private final GameService gameService;
    private final PlayerService playerService;
    private final CoachService coachService;

    @Autowired
    public TeamController(PlayerService playerService, CoachService coachService, GameService gameService) {
        this.playerService = playerService;
        this.coachService = coachService;
        this.gameService = gameService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView renderTeamView(ModelMap model, @RequestParam("name") String teamName) {
        model.addAttribute("teamName", teamName);
        model.addAttribute("players", playerService.getPlayersFrom(teamName));
        model.addAttribute("coaches", coachService.getCoachesFrom(teamName));
        model.addAttribute("record", gameService.getWinLossTieRecordFor(teamName));
        return new ModelAndView("team", model);
    }

}
