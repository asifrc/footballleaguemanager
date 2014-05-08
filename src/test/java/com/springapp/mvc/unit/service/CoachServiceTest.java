package com.springapp.mvc.unit.service;

import com.springapp.mvc.model.Coach;
import com.springapp.mvc.model.CoachBuilder;
import com.springapp.mvc.service.CoachService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CoachServiceTest {
    private CoachService coachService;
    private ArrayList<Coach> coachList;
    private Coach jack;

    @Before
    public void setUp() throws Exception {
        coachList = new ArrayList<Coach>();

        jack = new CoachBuilder()
                .withName("Jack")
                .withTeam("Cubs")
                .withPosition("Assistant Coach")
                .build();
        coachList.add(jack);

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

    @Test
    public void shouldReturnCoachesFromASpecificTeam() throws Exception {
        List<Coach> expectedCoaches = new ArrayList<Coach>();
        expectedCoaches.add(jack);
        List<Coach> actualCoaches = coachService.getCoachesFrom("Cubs");

        assertEquals(expectedCoaches, actualCoaches);
    }
}
