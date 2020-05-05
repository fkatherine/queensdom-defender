package com.katherinefreeman.queensdomdefender;

import androidx.lifecycle.ViewModel;

import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.event.TurnEndedEvent;
import com.katherinefreeman.queensdomdefender.event.UserCardPlayedEvent;
import com.katherinefreeman.queensdomdefender.game.model.Game;
import com.katherinefreeman.queensdomdefender.game.service.GameService;
import com.katherinefreeman.queensdomdefender.player.service.PlayerService;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ApplicationActivityViewModel extends ViewModel {

    private GameService gameService;
    private EventBus eventBus;
    private PlayerService playerService;

    private Game currentGame;

    @Inject
    public ApplicationActivityViewModel(GameService gameService, EventBus eventBus, PlayerService playerService) {
        this.gameService = gameService;
        this.eventBus = eventBus;
        this.playerService = playerService;

        eventBus.subscribe(this);
    }

    public void startNewGame() {
        currentGame = gameService.buildNewGame();
        eventBus.logGameEvent("New Game Started", R.color.applicationTextColour);

        playerService.drawFirstUserHand(currentGame.getUser());
        playerService.drawFirstOpponentHand(currentGame.getOpponent());

        gameService.startNewTurn(currentGame);
    }

    @Subscribe
    public void onUserCardPlayed(UserCardPlayedEvent event) {
        playerService.playUserCard(currentGame.getUser(), event.getCard());
    }

    @Subscribe
    public void onTurnEnded(TurnEndedEvent event) {
        gameService.endTurn(currentGame);
        gameService.startNewTurn(currentGame);
    }

}
