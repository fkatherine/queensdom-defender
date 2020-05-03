package com.katherinefreeman.queensdomdefender.game.service;

import com.katherinefreeman.queensdomdefender.R;
import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.game.model.Game;
import com.katherinefreeman.queensdomdefender.player.model.Player;
import com.katherinefreeman.queensdomdefender.player.model.PlayerType;
import com.katherinefreeman.queensdomdefender.player.service.PlayerService;

import org.junit.Test;
import org.mockito.InOrder;

import static com.katherinefreeman.queensdomdefender.player.model.PlayerType.HERO;
import static com.katherinefreeman.queensdomdefender.player.model.PlayerType.OPPONENT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GameServiceUnitTest {

    private PlayerService playerService = mock(PlayerService.class);
    private EventBus eventBus = mock(EventBus.class);

    private Player expectedNewPlayer = new Player();
    private Game game = new Game();

    private GameService target = new GameService(playerService, eventBus);

    @Test
    public void shouldBuildNewGameWithUserAsCurrentPlayerOnBuildNewGame() {
        given(playerService.createNewPlayer()).willReturn(expectedNewPlayer);

        Game newGame = target.buildNewGame();

        assertThat(newGame.getUser(), is(expectedNewPlayer));
        assertThat(newGame.getOpponent(), is(expectedNewPlayer));
        assertThat(newGame.getCurrentPlayer(), is(HERO));
    }

    @Test
    public void shouldLogTurnStartedForUserAndStartNewUserTurnWhenCurrentPlayerTypeIsHeroOnStartNewTurn() {
        withCurrentPlayer(HERO);
        Player user = new Player();
        game.setUser(user);

        target.startNewTurn(game);

        InOrder inOrder = inOrder(eventBus, playerService);
        inOrder.verify(eventBus).logGameEvent("You have started a new turn", R.color.applicationTextColour);
        inOrder.verify(playerService).startNewUserTurn(user);
    }

    @Test
    public void shouldLogTurnStartedForOpponentAndStartNewOpponentTurnWhenCurrentPlayerTypeIsOpponentOnStartNewTurn() {
        withCurrentPlayer(OPPONENT);
        Player opponent = new Player();
        game.setOpponent(opponent);

        target.startNewTurn(game);

        InOrder inOrder = inOrder(eventBus, playerService);
        inOrder.verify(eventBus).logGameEvent("Opponent has started a new turn", R.color.applicationTextColour);
        inOrder.verify(playerService).startNewOpponentTurn(opponent);
    }

    @Test
    public void shouldPostTurnStartedEventForCurrentPlayerAfterLoggingTurnStartedOnStartNewTurn() {
        withCurrentPlayer(HERO);

        target.startNewTurn(game);

        InOrder inOrder = inOrder(eventBus);
        inOrder.verify(eventBus).logGameEvent(anyString(), anyInt());
        inOrder.verify(eventBus).newTurnStarted(HERO);
    }

    @Test
    public void shouldLogTurnEndedForUserWhenCurrentPlayerIsHeroOnEndTurn() {
        withCurrentPlayer(HERO);

        target.endTurn(game);

        verify(eventBus).logGameEvent("You have ended your turn", R.color.applicationTextColour);
    }

    @Test
    public void shouldUpdateCurrentPlayerToOpponentWhenCurrentPlayerIsHeroOnEndTurn() {
        withCurrentPlayer(HERO);

        target.endTurn(game);

        assertThat(game.getCurrentPlayer(), is(OPPONENT));
    }

    @Test
    public void shouldLogTurnEndedForOpponentWhenCurrentPlayerIsOpponentOnEndTurn() {
        withCurrentPlayer(OPPONENT);

        target.endTurn(game);

        verify(eventBus).logGameEvent("Opponent has ended their turn", R.color.applicationTextColour);
    }

    @Test
    public void shouldUpdateCurrentPlayerToHeroWhenCurrentPlayerIsOpponentOnEndTurn() {
        withCurrentPlayer(OPPONENT);

        target.endTurn(game);

        assertThat(game.getCurrentPlayer(), is(HERO));
    }

    private void withCurrentPlayer(PlayerType playerType) {
        game.setCurrentPlayer(playerType);
    }
}