package com.springapp.mvc.controller;

import com.springapp.mvc.model.Coach;
import com.springapp.mvc.model.Player;
import com.springapp.mvc.service.CoachService;
import com.springapp.mvc.service.FileUploadService;
import com.springapp.mvc.service.PlayerService;
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
    FileUploadService fileUploadService;
    PlayerService playerService;
    CoachService coachService;

    @Autowired
    public FileUploadController(FileUploadService fileUploadService,
                                PlayerService playerService,
                                CoachService coachService) {
        this.fileUploadService = fileUploadService;
        this.playerService = playerService;
        this.coachService = coachService;
    }

    @RequestMapping(value = "/upload-playerlist", method = RequestMethod.POST)
    public ModelAndView handlePlayerUpload(@RequestParam("file") MultipartFile file, @RequestParam("person-type") String personType) {
        Set<Player> playerList;
        Set<Coach> coachList = coachService.getCoachList();
        try {
            playerList = fileUploadService.createPlayerList(file);
            playerService.setPlayerList(playerList);
            return redirectToHome(playerList, coachList);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return redirectToError(personType);
        }
    }

    @RequestMapping(value = "/upload-coachlist", method = RequestMethod.POST)
    public ModelAndView handleCoachUpload(@RequestParam("file") MultipartFile file, @RequestParam("person-type") String personType) {
        Set<Coach> coachList;
        Set<Player> playerList = playerService.getPlayerList();
        try {
            coachList = fileUploadService.createCoachList(file);
            coachService.setCoachList(coachList);
            return redirectToHome(playerList, coachList);
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
        if (personType.equals("player")) {
            exampleText = "Name,Team,Number,Age";
        } else if (personType.equals("coach")) {
            exampleText = "Name,Team,Title";
        }
        return exampleText;
    }

    private ModelAndView redirectToHome(Set<Player> playerList, Set<Coach> coachList) {
        ModelMap model = new ModelMap();
        model.addAttribute("playerList", playerList);
        model.addAttribute("coachList", coachList);
        return new ModelAndView("redirect:/", model);
    }

}