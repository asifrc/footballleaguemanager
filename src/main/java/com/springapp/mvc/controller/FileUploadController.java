package com.springapp.mvc.controller;

import com.springapp.mvc.model.Player;
import com.springapp.mvc.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FileUploadController {
    private FileUploadService fileUploadService;

    @Autowired
    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public ModelAndView handleUpload(Model model, @RequestParam("file") MultipartFile file) {
        List<Player> playerList = new ArrayList<Player>();
        if(!file.isEmpty()) {
            playerList = fileUploadService.createPlayerList(file);
        }

        model.addAttribute("playerList", playerList);
        return new ModelAndView("hello");
    }
}