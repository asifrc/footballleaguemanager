package com.springapp.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/team")
public class TeamController {
    @RequestMapping(method = RequestMethod.GET)
    public String showTeam(ModelMap modelMap, @RequestParam("name") String teamName) {
        modelMap.addAttribute("teamName", teamName);
        return "team";
    }
}
