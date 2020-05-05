package com.katherinefreeman.queensdomdefender.player.service;

import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.player.model.Player;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class OpponentService {

    private OpponentValidationService opponentValidationService;
    private OpponentCardService opponentCardService;
    private EventBus eventBus;

    @Inject
    public OpponentService(OpponentValidationService opponentValidationService, OpponentCardService opponentCardService, EventBus eventBus) {
        this.opponentValidationService = opponentValidationService;
        this.opponentCardService = opponentCardService;
        this.eventBus = eventBus;
    }

    public void startCardPlacementTurn(Player opponent) {
        if (opponentValidationService.canPlayBuildingCard(opponent)) {
            opponentCardService.placeBuildingCard(opponent);
        } else if (opponentValidationService.canPlayCharacterCard(opponent)) {
            opponentCardService.placeCharacterCard(opponent);
        }

        eventBus.turnEnded();
    }

}
