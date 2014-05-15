package com.springapp.mvc.controller;

import com.springapp.mvc.service.CoachService;
import com.springapp.mvc.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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


}