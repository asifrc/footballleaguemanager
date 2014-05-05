package com.springapp.mvc.unit.model;

import com.springapp.mvc.model.Player;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PlayerTest {

    @Test
    public void shouldReturnTrueIfTwoPlayersHaveSameAttributes() {
        Player player = new Player("Jack", "Team", "00", 21);
        Player samePlayer = new Player("Jack", "Team", "00", 21);

        assertThat(player.equals(samePlayer), is(true));
    }

    @Test
    public void shouldReturnFalseIfTwoPlayersHaveDifferentAttributes() {
        Player player = new Player("Jack", "Team", "00", 21);
        Player differentName = new Player("Jill", "Team", "00", 21);
        Player differentTeam = new Player("Jack", "Team1", "00", 21);
        Player differentNumber = new Player("Jack", "Team", "01", 21);
        Player differentAge = new Player("Jack", "Team", "00", 22);

        assertThat(player.equals(differentName), is(false));
        assertThat(player.equals(differentTeam), is(false));
        assertThat(player.equals(differentNumber), is(false));
        assertThat(player.equals(differentAge), is(false));
    }
}
