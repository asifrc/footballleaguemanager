package com.springapp.mvc.service;

import com.springapp.mvc.model.Coach;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CoachService {

    private Set<Coach> coaches;

    public CoachService() {
        this.coaches = new HashSet<Coach>();
    }

    public Set<Coach> getCoaches() {
        return coaches;
    }

    public void setCoaches(Set<Coach> coaches) {
        this.coaches = coaches;
    }

    public Set<Coach> getCoachesFrom(String teamName) {
        Set<Coach> filteredCoaches = new HashSet<Coach>();
        for (Coach coach : coaches) {
            if (coach.getTeam().equals(teamName)) {
                filteredCoaches.add(coach);
            }
        }
        return filteredCoaches;
    }
}
