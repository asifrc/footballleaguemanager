package com.springapp.mvc.controller;

import com.springapp.mvc.model.Player;
import com.springapp.mvc.service.CoachService;
import com.springapp.mvc.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
public class HomeController {

    private PlayerService playerService;
    private CoachService coachService;

    @Autowired
    public HomeController(PlayerService playerService, CoachService coachService) {
        this.playerService = playerService;
        this.coachService = coachService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView listPlayersAndCoaches(ModelMap model) {
        model.addAttribute("players", playerService.getPlayers());
        model.addAttribute("coaches", coachService.getCoaches());
        return new ModelAndView("home", model);
    }

    @RequestMapping(value = "/filterPlayers", method = RequestMethod.GET)
    public ModelAndView filterPlayers(ModelMap model, @RequestParam("minimum-age") String minimumAge) {
        int minAge = Integer.parseInt(minimumAge);
        Set<Player> filteredPlayers = playerService.getPlayersWithMinimumAge(minAge);

        model.addAttribute("minAge", minAge);
        model.addAttribute("players", filteredPlayers);
        model.addAttribute("coaches", coachService.getCoaches());

        return new ModelAndView("filteredPlayers", model);
    }

}