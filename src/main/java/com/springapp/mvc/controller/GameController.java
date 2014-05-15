package com.springapp.mvc.controller;

import com.springapp.mvc.model.Game;
import com.springapp.mvc.service.GameService;
import com.springapp.mvc.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GameController {
    private final TeamService teamService;
    private final GameService gameService;

    @Autowired
    public GameController(TeamService teamService, GameService gameService) {
        this.teamService = teamService;
        this.gameService = gameService;
    }

    @RequestMapping(value = "/record", method = RequestMethod.GET)
    public ModelAndView showRecordGameForm() {
        ModelMap model = new ModelMap();
        model.addAttribute("teams", teamService.getTeams());
        return new ModelAndView("recordGame", model);
    }

    @RequestMapping(value = "/record", method = RequestMethod.POST)
    public ModelAndView handleRecordGameForm(@ModelAttribute Game game, final RedirectAttributes redirectAttributes) {
        gameService.addGame(game);
        redirectAttributes.addFlashAttribute("gameResult", game.displayResults());
        return new ModelAndView("redirect:/record");
    }

}