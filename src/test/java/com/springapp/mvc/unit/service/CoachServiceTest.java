package com.springapp.mvc.unit.service;

import com.springapp.mvc.model.Coach;
import com.springapp.mvc.model.CoachBuilder;
import com.springapp.mvc.service.CoachService;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CoachServiceTest {
    private CoachService coachService;
    private HashSet<Coach> coachList;
    private Coach jack;

    @Before
    public void setUp() {
        coachList = new HashSet<Coach>();

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
    public void shouldReturnListOfCoaches() {
        assertEquals(coachList, coachService.getCoachList());
    }

    @Test
    public void shouldReturnCoachesFromASpecificTeam() {
        Set<Coach> expectedCoaches = new HashSet<Coach>();
        expectedCoaches.add(jack);
        Set<Coach> actualCoaches = coachService.getCoachesFrom("Cubs");

        assertEquals(expectedCoaches, actualCoaches);
    }
}
