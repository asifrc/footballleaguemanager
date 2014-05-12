package com.springapp.mvc.service;

import com.springapp.mvc.model.Coach;
import com.springapp.mvc.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Component
public class TeamService {
    private final PlayerService playerService;
    private final CoachService coachService;

    @Autowired
    public TeamService(PlayerService playerService, CoachService coachService) {

        this.playerService = playerService;
        this.coachService = coachService;
    }

    public Collection<String> getTeamList() {
        List<Player> playerList = playerService.getPlayerList();
        List<Coach> coachList = coachService.getCoachList();
        Collection<String> teams = new HashSet<String>();
        for (Player player : playerList) {
            teams.add(player.getTeam());
        }
        for (Coach coach : coachList) {
            teams.add(coach.getTeam());
        }
        return teams;
    }
}
