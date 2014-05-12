package com.springapp.mvc.controller;

import com.springapp.mvc.model.Player;
import com.springapp.mvc.service.CoachService;
import com.springapp.mvc.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private PlayerService playerService;
    private CoachService coachService;

    @Autowired
    public HomeController(PlayerService playerService, CoachService coachService) {
        this.playerService = playerService;
        this.coachService = coachService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listPlayersAndCoaches(ModelMap model) {
        model.addAttribute("playerList", playerService.getPlayerList());
        model.addAttribute("coachList", coachService.getCoachList());
        return new ModelAndView("home", model);
    }

    @RequestMapping(value = "/filterPlayers", method = RequestMethod.GET)
    public ModelAndView filterPlayers(ModelMap model, HttpServletRequest request) {
        int minAge = Integer.parseInt(request.getParameter("minimum-age"));
        List<Player> filteredPlayerList = playerService.getPlayersWithMinimumAge(minAge);

        model.addAttribute("minAge", minAge);
        model.addAttribute("playerList", filteredPlayerList);
        model.addAttribute("coachList", coachService.getCoachList());

        return new ModelAndView("filteredPlayerList", model);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String showFindPage() {
        return "findPlayer";
    }

    @RequestMapping(value = {"/find"}, method = RequestMethod.GET, params = "Find=find")
    public ModelAndView findPlayer(@ModelAttribute Player player) {
        ModelMap model = new ModelMap();
        String number = player.getNumber();
        Player foundPlayer = playerService.findPlayerByName(player.getName());
        ModelAndView modelAndView = new ModelAndView("findPlayer");

        if (foundPlayer != null) {
            if (foundPlayer.getNumber().equals(number)) {
                modelAndView.getModel().put("playerFound", true);
                modelAndView.addObject("player", foundPlayer);
            } else {
                modelAndView.addObject("error", "Sorry, that name and number do not match. Please try again.");
            }
        } else {
            modelAndView.addObject("error", "Sorry, there is no player with that name and number");
        }

        return modelAndView;
    }
}