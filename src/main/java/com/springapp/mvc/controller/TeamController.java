package com.springapp.mvc.controller;

import com.springapp.mvc.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/team")
public class TeamController {
    private PlayerService playerService;

    @Autowired
    public TeamController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showTeam(ModelMap modelMap, @RequestParam("name") String teamName) {
        modelMap.addAttribute("teamName", teamName);
        modelMap.addAttribute("playerList", playerService.getPlayerList());
        return "team";
    }
}
