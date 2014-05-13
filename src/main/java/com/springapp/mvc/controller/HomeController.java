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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

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