package com.katherinefreeman.queensdomdefender;

import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.card.service.CardService;
import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.game.model.Game;
import com.katherinefreeman.queensdomdefender.game.service.GameService;
import com.katherinefreeman.queensdomdefender.player.model.Player;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

public class ApplicationActivityViewModelUnitTest {

    private GameService gameService = mock(GameService.class);
    private EventBus eventBus = mock(EventBus.class);
    private CardService cardService = mock(CardService.class);

    private Game expectedNewGame = new Game();
    private Player expectedUser = new Player();
    private ArrayList<Card> expectedUserHand = new ArrayList<>();
    private Player expectedOpponent = new Player();

    private ApplicationActivityViewModel target = new ApplicationActivityViewModel(gameService, eventBus, cardService);

    @Before
    public void setUp() {
        expectedUser.setHand(expectedUserHand);
        expectedNewGame.setUser(expectedUser);
        expectedNewGame.setOpponent(expectedOpponent);
        given(gameService.buildNewGame()).willReturn(expectedNewGame);
    }

    @Test
    public void shouldStartNewGameAndLogGameStartEvent() {
        target.startNewGame();

        InOrder inOrder = inOrder(gameService, eventBus);
        inOrder.verify(gameService).buildNewGame();
        inOrder.verify(eventBus).logGameEvent("New Game Started", R.color.applicationTextColour);
    }

    @Test
    public void shouldDrawConfiguredMaximumCardsForUserOnStartNewGame() {
        target.startNewGame();

        InOrder inOrder = inOrder(cardService, eventBus);
        inOrder.verify(cardService).drawCardsIntoHand(expectedUser, 3);
        inOrder.verify(eventBus).userCardsDrawn(expectedUserHand);
        inOrder.verify(eventBus).logGameEvent("You drew 3 cards", R.color.applicationTextColour);
    }

    @Test
    public void shouldDrawConfiguredMaximumCardsForOpponentOnStartNewGame() {
        target.startNewGame();

        InOrder inOrder = inOrder(cardService, eventBus);
        inOrder.verify(cardService).drawCardsIntoHand(expectedOpponent, 3);
        inOrder.verify(eventBus).logGameEvent("Opponent drew 3 cards", R.color.applicationTextColour);
    }

}