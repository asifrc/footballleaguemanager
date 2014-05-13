package com.springapp.mvc.service;

import com.springapp.mvc.model.Coach;
import com.springapp.mvc.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TeamService {
    private final PlayerService playerService;
    private final CoachService coachService;

    @Autowired
    public TeamService(PlayerService playerService, CoachService coachService) {
        this.playerService = playerService;
        this.coachService = coachService;
    }

    public Set<String> getTeams() {
        Set<Player> players = playerService.getPlayers();
        Set<Coach> coaches = coachService.getCoaches();
        Set<String> teams = new HashSet<String>();
        for (Player player : players) {
            teams.add(player.getTeam());
        }
        for (Coach coach : coaches) {
            teams.add(coach.getTeam());
        }
        return teams;
    }
}
