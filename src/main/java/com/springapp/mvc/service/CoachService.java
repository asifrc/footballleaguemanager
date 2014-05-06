package com.springapp.mvc.service;

import com.springapp.mvc.model.Coach;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CoachService {

    private ArrayList<Coach> coachList;

    public List<Coach> getCoachList() {
        List<Coach> coachList = new ArrayList<Coach>();
        coachList.add(new Coach("Jack", "Team1", "Assistant Coach"));
        coachList.add(new Coach("Jill", "Team2", "Head Coach"));
        return coachList;
    }

    public void setCoachList(ArrayList<Coach> coachList) {
        this.coachList = coachList;
    }
}
