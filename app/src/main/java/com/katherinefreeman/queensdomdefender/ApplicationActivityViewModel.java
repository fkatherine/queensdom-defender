package com.katherinefreeman.queensdomdefender;

import androidx.lifecycle.ViewModel;

import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.game.model.Game;
import com.katherinefreeman.queensdomdefender.game.service.GameService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ApplicationActivityViewModel extends ViewModel {

    private GameService gameService;
    private EventBus eventBus;

    private Game currentGame;

    @Inject
    public ApplicationActivityViewModel(GameService gameService, EventBus eventBus) {
        this.gameService = gameService;
        this.eventBus = eventBus;
    }

    public void startNewGame() {
        currentGame = gameService.buildNewGame();
        eventBus.logGameEvent("New Game Started", R.color.applicationTextColour);
    }

}
