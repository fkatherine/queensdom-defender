package com.katherinefreeman.queensdomdefender.game.service;

import com.katherinefreeman.queensdomdefender.game.model.Game;
import com.katherinefreeman.queensdomdefender.player.model.Player;
import com.katherinefreeman.queensdomdefender.player.service.PlayerService;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.katherinefreeman.queensdomdefender.player.model.PlayerType.HERO;

@Singleton
public class GameService {

    private PlayerService playerService;

    @Inject
    public GameService(PlayerService playerService) {
        this.playerService = playerService;
    }

    public Game buildNewGame() {
        Player user = playerService.createNewPlayer();
        Player opponent = playerService.createNewPlayer();

        Game newGame = new Game();
        newGame.setUser(user);
        newGame.setOpponent(opponent);
        newGame.setCurrentPlayer(HERO);

        return newGame;
    }
}
