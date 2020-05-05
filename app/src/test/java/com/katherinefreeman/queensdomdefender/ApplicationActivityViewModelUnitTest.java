package com.katherinefreeman.queensdomdefender;

import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.event.TurnEndedEvent;
import com.katherinefreeman.queensdomdefender.event.UserCardPlayedEvent;
import com.katherinefreeman.queensdomdefender.game.model.Game;
import com.katherinefreeman.queensdomdefender.game.service.GameService;
import com.katherinefreeman.queensdomdefender.player.model.Player;
import com.katherinefreeman.queensdomdefender.player.service.PlayerService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static com.katherinefreeman.queensdomdefender.card.model.CardType.CHARACTER;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ApplicationActivityViewModelUnitTest {

    private GameService gameService = mock(GameService.class);
    private EventBus eventBus = mock(EventBus.class);
    private PlayerService playerService = mock(PlayerService.class);

    private Game expectedNewGame = new Game();
    private Player expectedUser = new Player();
    private Player expectedOpponent = new Player();
    private Card card = new Card("archer", 1, 1, 1, 1, CHARACTER);

    private ApplicationActivityViewModel target = new ApplicationActivityViewModel(gameService, eventBus, playerService);

    @Before
    public void setUp() {
        expectedNewGame.setUser(expectedUser);
        expectedNewGame.setOpponent(expectedOpponent);
        given(gameService.buildNewGame()).willReturn(expectedNewGame);
    }

    @Test
    public void shouldSubscribeToEventBusOnInit() {
        verify(eventBus).subscribe(target);
    }

    @Test
    public void shouldBuildNewGameAndLogGameStartEventOnStartNewGame() {
        target.startNewGame();

        InOrder inOrder = inOrder(gameService, eventBus);
        inOrder.verify(gameService).buildNewGame();
        inOrder.verify(eventBus).logGameEvent("New Game Started", R.color.applicationTextColour);
    }

    @Test
    public void shouldDrawPlayersFirstHandAfterBuildingNewGameOnStartNewGame() {
        target.startNewGame();

        InOrder inOrder = inOrder(gameService, playerService);
        inOrder.verify(gameService).buildNewGame();
        inOrder.verify(playerService).drawFirstUserHand(expectedUser);
        inOrder.verify(playerService).drawFirstOpponentHand(expectedOpponent);
    }

    @Test
    public void shouldStartNewTurnForCurrentPlayerAfterDrawingPlayersFirstHandOnStartNewGame() {
        target.startNewGame();

        InOrder inOrder = inOrder(playerService, gameService);
        inOrder.verify(playerService).drawFirstUserHand(expectedUser);
        inOrder.verify(playerService).drawFirstOpponentHand(expectedOpponent);
        inOrder.verify(gameService).startNewTurn(expectedNewGame);
    }

    @Test
    public void shouldPlayUserCardOnUserCardPlayedEvent() {
        withGameStarted();

        target.onUserCardPlayed(new UserCardPlayedEvent(card));

        verify(playerService).playUserCard(expectedUser, card);
    }

    @Test
    public void shouldEndTurnAndStartNewTurnOnTurnEndedEvent() {
        withGameStarted();

        target.onTurnEnded(new TurnEndedEvent());

        InOrder inOrder = inOrder(gameService);
        inOrder.verify(gameService).endTurn(expectedNewGame);
        inOrder.verify(gameService).startNewTurn(expectedNewGame);
    }

    private void withGameStarted() {
        target.startNewGame();
    }

}
