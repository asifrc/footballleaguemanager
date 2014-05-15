package com.springapp.mvc.controller;

import com.springapp.mvc.model.Player;
import com.springapp.mvc.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlayerFinderController {
    private final PlayerService playerService;

    @Autowired
    public PlayerFinderController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String showFindPage() {
        return "findPlayer";
    }

    @RequestMapping(value = {"/find"}, method = RequestMethod.GET, params = "Find=find")
    public ModelAndView findPlayer(@ModelAttribute Player player) {
        ModelMap model = new ModelMap();
        Player foundPlayer = playerService.findPlayerByName(player.getName());

        if (foundPlayer != null) {
            if (foundPlayer.getNumber().equals(player.getNumber())) {
                model.addAttribute("playerFound", true);
                model.addAttribute("player", foundPlayer);
            } else {
                model.addAttribute("error", "Sorry, that name and number do not match. Please try again.");
            }
        } else {
            model.addAttribute("error", "Sorry, there is no player with that name and number");
        }

        return new ModelAndView("findPlayer", model);
    }
}
