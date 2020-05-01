package com.katherinefreeman.queensdomdefender.gamelog.view;

import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.gamelog.model.GameLogItem;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GameLogFragmentViewModelUnitTest {

    private EventBus eventBus = mock(EventBus.class);

    private GameLogFragmentViewModel target = new GameLogFragmentViewModel(eventBus);

    @Test
    public void shouldSubscribeToEventBusOnInit() {
        verify(eventBus).subscribe(target);
    }

    @Test
    public void shouldAddGameLogItemToGameLogItemsOnGameLogEvent() {
        GameLogItem gameLogItem = new GameLogItem("Begin Game", 0);

        target.onLogGameEvent(gameLogItem);

        assertThat(target.gameLogItems.contains(gameLogItem), is(true));
    }
}