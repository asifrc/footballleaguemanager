package com.springapp.mvc.controller;

import com.springapp.mvc.model.Player;
import com.springapp.mvc.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @RequestMapping(method = RequestMethod.GET)
	public String listPlayers(ModelMap model) {
        List<Player> playerList = playerService.getPlayerList();
        model.addAttribute("playerList", playerList);
		return "hello";
	}

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String showFindPage() {
        return "findPlayer";
    }

    public ModelAndView findPlayer(Player player) {
        String name = player.getName();
        String number = player.getNumber();
        Player foundPlayer = playerService.findPlayer(name, number);
        ModelAndView modelAndView = new ModelAndView("findPlayer");
        modelAndView.addObject("player", foundPlayer);
        return modelAndView;
    }
}
