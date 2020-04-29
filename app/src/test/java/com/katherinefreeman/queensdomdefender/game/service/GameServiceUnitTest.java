package com.katherinefreeman.queensdomdefender.game.service;

import com.katherinefreeman.queensdomdefender.game.model.Game;
import com.katherinefreeman.queensdomdefender.player.model.Player;
import com.katherinefreeman.queensdomdefender.player.service.PlayerService;

import org.junit.Test;

import static com.katherinefreeman.queensdomdefender.player.model.PlayerType.HERO;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GameServiceUnitTest {

    private PlayerService playerService = mock(PlayerService.class);

    private Player expectedNewPlayer = new Player();

    private GameService target = new GameService(playerService);

    @Test
    public void shouldBuildNewGameWithUserAsCurrentPlayerOnBuildNewGame() {
        given(playerService.createNewPlayer()).willReturn(expectedNewPlayer);

        Game newGame = target.buildNewGame();

        assertThat(newGame.getUser(), is(expectedNewPlayer));
        assertThat(newGame.getOpponent(), is(expectedNewPlayer));
        assertThat(newGame.getCurrentPlayer(), is(HERO));
    }
}