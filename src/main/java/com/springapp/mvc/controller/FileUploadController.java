package com.springapp.mvc.controller;

import com.springapp.mvc.model.Coach;
import com.springapp.mvc.model.Player;
import com.springapp.mvc.service.CoachService;
import com.springapp.mvc.service.FileUploadService;
import com.springapp.mvc.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FileUploadController {
    FileUploadService fileUploadService;
    PlayerService playerService;
    CoachService coachService;

    @Autowired
    public FileUploadController(FileUploadService fileUploadService, PlayerService playerService) {
        this.fileUploadService = fileUploadService;
        this.playerService = playerService;
        this.coachService = coachService;
    }

    @RequestMapping(value="/upload-playerlist", method = RequestMethod.POST)
    public ModelAndView handlePlayerUpload(@RequestParam("file") MultipartFile file) {
        List<Player> playerList = null;
        try {
            playerList = fileUploadService.createPlayerList(file);
            playerService.setPlayerList(playerList);
            return redirectToHome(playerList);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return redirectToErrorPage();
        }
    }

    @RequestMapping(value="/upload-coachlist", method = RequestMethod.POST)
    public ModelAndView handleCoachUpload(@RequestParam("file") MultipartFile file) {
        List<Coach> coachList = null;
        try {
            return new ModelAndView("redirect:/");
        } catch (RuntimeException e) {
            e.printStackTrace();
            return redirectToErrorPage();
        }
    }
    @RequestMapping(value="/error", method = RequestMethod.GET)
    public String showError() {
        return "error";
    }

    private ModelAndView redirectToHome(List<Player> playerList) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("playerList", playerList);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    private ModelAndView redirectToErrorPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/error");
        return modelAndView;
    }
}