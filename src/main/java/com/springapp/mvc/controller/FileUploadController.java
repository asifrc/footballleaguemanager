package com.springapp.mvc.controller;

import com.springapp.mvc.model.Coach;
import com.springapp.mvc.model.Player;
import com.springapp.mvc.service.CoachService;
import com.springapp.mvc.service.FileUploadService;
import com.springapp.mvc.service.PlayerService;
import com.springapp.mvc.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
public class FileUploadController {
    private FileUploadService fileUploadService;
    private PlayerService playerService;
    private CoachService coachService;
    private TeamService teamService;

    @Autowired
    public FileUploadController(FileUploadService fileUploadService,
                                PlayerService playerService,
                                CoachService coachService,
                                TeamService teamService) {
        this.fileUploadService = fileUploadService;
        this.playerService = playerService;
        this.coachService = coachService;
        this.teamService = teamService;
    }

    @RequestMapping(value = "/upload-players", method = RequestMethod.POST)
    public ModelAndView handlePlayerUpload(@RequestParam("file") MultipartFile file, @RequestParam("person-type") String personType) {
        Set<Player> players = null;
        Set<Coach> coaches = coachService.getCoaches();
        try {
            players = fileUploadService.createPlayersFrom(file);
            playerService.setPlayers(players);
            teamService.clearTeams();
            return redirectToHome(players, coaches);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return redirectToError(personType);
        }
    }

    @RequestMapping(value = "/upload-coaches", method = RequestMethod.POST)
    public ModelAndView handleCoachUpload(@RequestParam("file") MultipartFile file, @RequestParam("person-type") String personType) {
        Set<Coach> coaches = null;
        Set<Player> players = playerService.getPlayers();
        try {
            coaches = fileUploadService.createCoachesFrom(file);
            coachService.setCoaches(coaches);
            teamService.clearTeams();
            return redirectToHome(players, coaches);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return redirectToError(personType);
        }
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView showError(@RequestParam("personType") String personType) {
        ModelMap model = new ModelMap();
        model.addAttribute("exampleText", exampleTextFor(personType));
        return new ModelAndView("error", model);
    }

    private ModelAndView redirectToError(String personType) {
        return new ModelAndView("redirect:/error?personType=" + personType);
    }

    private String exampleTextFor(String personType) {
        String exampleText = null;
        if (personType.equals("players")) {
            exampleText = "Name,Team,Number,Age";
        } else if (personType.equals("coaches")) {
            exampleText = "Name,Team,Title";
        }
        return exampleText;
    }

    private ModelAndView redirectToHome(Set<Player> players, Set<Coach> coaches) {
        ModelMap model = new ModelMap();
        model.addAttribute("players", players);
        model.addAttribute("coaches", coaches);
        return new ModelAndView("redirect:/", model);
    }

}