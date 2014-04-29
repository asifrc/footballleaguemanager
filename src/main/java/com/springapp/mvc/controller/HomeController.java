package com.springapp.mvc.controller;

import com.springapp.mvc.model.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
	@RequestMapping(method = RequestMethod.GET)
	public String listPlayers(ModelMap model) {
        List<Player> playerList = new ArrayList<Player>();
        playerList.add(new Player("Bob"));
        playerList.add(new Player("Sally"));
		model.addAttribute("playerList", playerList);
		return "hello";
	}
}