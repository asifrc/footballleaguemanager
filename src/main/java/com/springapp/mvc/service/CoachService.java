package com.springapp.mvc.service;

import com.springapp.mvc.model.Coach;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CoachService {

    private Set<Coach> coachList;

    public CoachService() {
        this.coachList = new HashSet<Coach>();
    }

    public Set<Coach> getCoachList() {
        return coachList;
    }

    public void setCoachList(Set<Coach> coachList) {
        this.coachList = coachList;
    }

    public Set<Coach> getCoachesFrom(String teamName) {
        Set<Coach> filteredCoaches = new HashSet<Coach>();
        for (Coach coach : coachList) {
            if (coach.getTeam().equals(teamName)) {
                filteredCoaches.add(coach);
            }
        }
        return filteredCoaches;
    }
}
