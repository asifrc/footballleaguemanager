package com.springapp.mvc.controller;

import com.springapp.mvc.model.Player;
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

    @Autowired
    public FileUploadController(FileUploadService fileUploadService, PlayerService playerService) {
        this.fileUploadService = fileUploadService;
        this.playerService = playerService;
    }

    @RequestMapping(value="/upload", method= RequestMethod.POST)
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile file) {
        List<Player> playerList = fileUploadService.createPlayerList(file);
        playerService.setPlayerList(playerList);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("playerList", playerList);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}