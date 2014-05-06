package com.springapp.mvc.unit.service;

import com.springapp.mvc.model.Coach;
import com.springapp.mvc.model.CoachBuilder;
import com.springapp.mvc.service.CoachService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CoachServiceTest {
    private CoachService coachService;
    private ArrayList<Coach> coachList;

    @Before
    public void setUp() throws Exception {
        coachList = new ArrayList<Coach>();

        coachList.add(new CoachBuilder()
                .withName("Jack")
                .withTeam("Team1")
                .withPosition("Assistant Coach")
                .build());

        coachList.add(new CoachBuilder()
                .withName("Jill")
                .withTeam("Team2")
                .withPosition("Head Coach")
                .build());

        coachService = new CoachService();
        coachService.setCoachList(coachList);
    }

    @Test
    public void shouldReturnListOfCoaches() throws Exception {
        assertEquals(coachList, coachService.getCoachList());
    }
}
