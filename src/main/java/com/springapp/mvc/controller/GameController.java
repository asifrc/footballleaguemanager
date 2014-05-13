package com.springapp.mvc.controller;

import com.springapp.mvc.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GameController {
    private final TeamService teamService;

    @Autowired
    public GameController(TeamService teamService) {

        this.teamService = teamService;
    }

    @RequestMapping("/record")
    public ModelAndView showRecordGameForm() {
        ModelMap model = new ModelMap();
        model.addAttribute("teams", teamService.getTeams());
        return new ModelAndView("recordGame", model);
    }
}
