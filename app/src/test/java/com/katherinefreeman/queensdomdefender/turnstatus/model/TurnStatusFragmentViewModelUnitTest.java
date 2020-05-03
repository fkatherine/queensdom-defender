package com.katherinefreeman.queensdomdefender.turnstatus.model;

import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.event.TurnStartedEvent;
import com.katherinefreeman.queensdomdefender.turnstatus.view.TurnStatusFragmentViewModel;

import org.junit.Test;

import static com.katherinefreeman.queensdomdefender.player.model.PlayerType.HERO;
import static com.katherinefreeman.queensdomdefender.player.model.PlayerType.OPPONENT;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TurnStatusFragmentViewModelUnitTest {

    private EventBus eventBus = mock(EventBus.class);

    private TurnStatusFragmentViewModel target = new TurnStatusFragmentViewModel(eventBus);

    @Test
    public void shouldSetCurrentPlayerTypeDefaultToHero() {
        assertThat(target.currentPlayer.get(), is(HERO));
    }

    @Test
    public void shouldSubscribeToEventBusOnInit() {
        verify(eventBus).subscribe(target);
    }

    @Test
    public void shouldUpdateCurrentPlayerOnTurnStartedEvent() {
        target.currentPlayer.set(HERO);

        target.onTurnStarted(new TurnStartedEvent(OPPONENT));

        assertThat(target.currentPlayer.get(), is(OPPONENT));
    }

    @Test
    public void shouldPostTurnEndedEventOnEndTurn() {
        target.onEndTurn();

        verify(eventBus).turnEnded();
    }
}