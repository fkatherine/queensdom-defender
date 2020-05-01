package com.katherinefreeman.queensdomdefender;

import androidx.lifecycle.ViewModel;

import com.katherinefreeman.queensdomdefender.card.service.CardService;
import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.game.model.Game;
import com.katherinefreeman.queensdomdefender.game.service.GameService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ApplicationActivityViewModel extends ViewModel {

    private final int CONFIGURED_MAXIMUM_DRAWABLE_CARD_COUNT = 3;

    private GameService gameService;
    private EventBus eventBus;
    private CardService cardService;

    private Game currentGame;

    @Inject
    public ApplicationActivityViewModel(GameService gameService, EventBus eventBus, CardService cardService) {
        this.gameService = gameService;
        this.eventBus = eventBus;
        this.cardService = cardService;
    }

    public void startNewGame() {
        currentGame = gameService.buildNewGame();
        eventBus.logGameEvent("New Game Started", R.color.applicationTextColour);

        drawFirstUserHand();
        drawFirstOpponentHand();
    }

    private void drawFirstOpponentHand() {
        cardService.drawCardsIntoHand(currentGame.getOpponent(), CONFIGURED_MAXIMUM_DRAWABLE_CARD_COUNT);
        eventBus.logGameEvent(String.format("Opponent drew %d cards", CONFIGURED_MAXIMUM_DRAWABLE_CARD_COUNT), R.color.applicationTextColour);
    }

    private void drawFirstUserHand() {
        cardService.drawCardsIntoHand(currentGame.getUser(), CONFIGURED_MAXIMUM_DRAWABLE_CARD_COUNT);
        eventBus.userCardsDrawn(currentGame.getUser().getHand());
        eventBus.logGameEvent(String.format("You drew %d cards", CONFIGURED_MAXIMUM_DRAWABLE_CARD_COUNT), R.color.applicationTextColour);
    }

}
