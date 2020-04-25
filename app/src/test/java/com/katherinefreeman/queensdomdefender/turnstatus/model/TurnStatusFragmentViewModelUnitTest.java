package com.katherinefreeman.queensdomdefender.turnstatus.model;

import org.junit.Test;


import static com.katherinefreeman.queensdomdefender.player.model.PlayerType.HERO;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TurnStatusFragmentViewModelUnitTest {
    private TurnStatusFragmentViewModel target = new TurnStatusFragmentViewModel();

    @Test
    public void shouldSetCurrentPlayerTypeDefaultToHero() {
        assertThat(target.currentPlayerType, is(HERO));
    }
}