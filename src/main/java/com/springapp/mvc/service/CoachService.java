package com.springapp.mvc.service;

import com.springapp.mvc.model.Coach;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoachService {

    private List<Coach> coachList;

    public List<Coach> getCoachList() {
        return coachList;
    }

    public void setCoachList(List<Coach> coachList) {
        this.coachList = coachList;
    }
}
