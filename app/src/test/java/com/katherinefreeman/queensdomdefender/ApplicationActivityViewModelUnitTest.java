package com.katherinefreeman.queensdomdefender;

import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.game.service.GameService;

import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

public class ApplicationActivityViewModelUnitTest {

    private GameService gameService = mock(GameService.class);
    private EventBus eventBus = mock(EventBus.class);

    private ApplicationActivityViewModel target = new ApplicationActivityViewModel(gameService, eventBus);

    @Test
    public void shouldStartNewGameAndLogGameStartEvent() {
        target.startNewGame();

        InOrder inOrder = inOrder(gameService, eventBus);
        inOrder.verify(gameService).buildNewGame();
        inOrder.verify(eventBus).logGameEvent("New Game Started", R.color.applicationTextColour);
    }
}