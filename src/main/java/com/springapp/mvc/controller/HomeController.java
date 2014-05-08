package com.springapp.mvc.controller;

import com.springapp.mvc.model.Coach;
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
    public String listPlayersAndCoaches(ModelMap model, HttpServletRequest request) {
        List<Player> playerList = (List<Player>)request.getSession().getAttribute("playerList");
        request.getSession().removeAttribute("playerList");

        if(playerList == null) {
            playerList = playerService.getPlayerList();
        }

        List<Coach> coachList = coachService.getCoachList();

        model.addAttribute("playerList", playerList);
        model.addAttribute("coachList", coachList);
        return "home";
    }

    @RequestMapping(value = "/filterPlayers", method = RequestMethod.POST)
    public ModelAndView filterPlayers(ModelMap model, HttpServletRequest request) {
        List<Player> playerList = playerService.getPlayersWithMinimumAge(18);
        List<Coach> coachList = coachService.getCoachList();

        request.getSession().setAttribute("playerList", playerList);
        request.getSession().setAttribute("coachList", coachList);

        return new ModelAndView("redirect:/", model);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String showFindPage() {
        return "findPlayer";
    }

    @RequestMapping(value = {"/find"}, method = RequestMethod.GET, params = "Find=find")
    public ModelAndView findPlayer(@ModelAttribute Player player) {
        String name = player.getName();
        String number = player.getNumber();
        Player foundPlayer = playerService.findPlayerByName(name);
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
