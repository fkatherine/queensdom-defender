package com.katherinefreeman.queensdomdefender.game.service;

import com.katherinefreeman.queensdomdefender.R;
import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.game.model.Game;
import com.katherinefreeman.queensdomdefender.player.model.Player;
import com.katherinefreeman.queensdomdefender.player.model.PlayerType;
import com.katherinefreeman.queensdomdefender.player.service.PlayerService;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.katherinefreeman.queensdomdefender.player.model.PlayerType.HERO;
import static com.katherinefreeman.queensdomdefender.player.model.PlayerType.OPPONENT;

@Singleton
public class GameService {

    private PlayerService playerService;
    private EventBus eventBus;

    @Inject
    public GameService(PlayerService playerService, EventBus eventBus) {
        this.playerService = playerService;
        this.eventBus = eventBus;
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

    public void startNewTurn(Game game) {
        PlayerType currentPlayer = game.getCurrentPlayer();

        if (currentPlayer == HERO) {
            eventBus.logGameEvent("You have started a new turn", R.color.applicationTextColour);
            playerService.startNewUserTurn(game.getUser());
        } else {
            eventBus.logGameEvent("Opponent has started a new turn", R.color.applicationTextColour);
            playerService.startNewOpponentTurn(game.getOpponent());
        }

        eventBus.newTurnStarted(currentPlayer);
    }

    public void endTurn(Game game) {
        PlayerType currentPlayer = game.getCurrentPlayer();
        if (currentPlayer == HERO) {
            eventBus.logGameEvent("You have ended your turn", R.color.applicationTextColour);
            game.setCurrentPlayer(OPPONENT);
        } else {
            eventBus.logGameEvent("Opponent has ended their turn", R.color.applicationTextColour);
            game.setCurrentPlayer(HERO);
        }
    }

}
