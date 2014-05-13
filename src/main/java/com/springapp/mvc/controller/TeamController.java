package com.springapp.mvc.controller;

import com.springapp.mvc.service.CoachService;
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
    private PlayerService playerService;
    private CoachService coachService;

    @Autowired
    public TeamController(PlayerService playerService, CoachService coachService) {
        this.playerService = playerService;
        this.coachService = coachService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showTeam(ModelMap model, @RequestParam("name") String teamName) {
        model.addAttribute("teamName", teamName);
        model.addAttribute("players", playerService.getPlayersFrom(teamName));
        model.addAttribute("coaches", coachService.getCoachesFrom(teamName));
        return new ModelAndView("team", model);
    }
}
