package com.springapp.mvc.model;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void playerShouldHaveAName() {
        Player player = new Player("Sally");
        assertThat(player.getName(), is("Sally"));
    }

}