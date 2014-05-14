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
    private Set<String> teams;


    @Autowired
    public TeamService(PlayerService playerService, CoachService coachService) {
        this.playerService = playerService;
        this.coachService = coachService;
        this.teams = new HashSet<String>();
    }

    public void clearTeams() {
        this.teams = new HashSet<String>();
    }

    public Set<String> getTeams() {
        updateTeams();
        return teams;
    }

    private void updateTeams() {
        Set<Player> players = playerService.getPlayers();
        Set<Coach> coaches = coachService.getCoaches();
        for (Player player : players) {
            teams.add(player.getTeam());
        }
        for (Coach coach : coaches) {
            teams.add(coach.getTeam());
        }
    }
}
