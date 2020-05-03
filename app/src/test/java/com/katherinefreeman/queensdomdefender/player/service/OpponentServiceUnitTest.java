package com.katherinefreeman.queensdomdefender.player.service;

import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.player.model.Player;

import org.junit.Test;

import static com.katherinefreeman.queensdomdefender.player.model.PlayerType.OPPONENT;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OpponentServiceUnitTest {

    private EventBus eventBus = mock(EventBus.class);

    private Player opponent = new Player();

    private OpponentService target = new OpponentService(eventBus);

    @Test
    public void shouldPostCardPlacementStageStartedEventForOpponentOnStartCardPlacementTurn() {
        target.startCardPlacementTurn(opponent);

        verify(eventBus).playerCardPlacementStageStarted(OPPONENT);
    }

    @Test
    public void shouldPostTurnEndedEventOnStartCardPlacementTurn() {
        target.startCardPlacementTurn(opponent);

        verify(eventBus).turnEnded();
    }

}
