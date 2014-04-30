package com.springapp.mvc.controller;

import com.springapp.mvc.service.FileUploadService;
import com.springapp.mvc.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
    private final PlayerService playerService;
    private final FileUploadService fileUploadService;

    @Autowired
    public FileUploadController(FileUploadService fileUploadService, PlayerService playerService) {
        this.fileUploadService = fileUploadService;
        this.playerService = playerService;
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public String handleUpload(Model model, @RequestParam("file") MultipartFile file) {
        if(!file.isEmpty()) {
            playerService.setPlayerList(fileUploadService.createPlayerList(file));
        }

        return "redirect:/";
    }
}