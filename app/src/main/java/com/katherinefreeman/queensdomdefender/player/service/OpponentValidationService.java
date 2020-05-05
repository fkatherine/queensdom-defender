package com.katherinefreeman.queensdomdefender.player.service;

import com.katherinefreeman.queensdomdefender.player.model.Player;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class OpponentValidationService {

    private PlayerValidationService playerValidationService;
    private PlayerCardSearchService playerCardSearchService;

    @Inject
    public OpponentValidationService(PlayerValidationService playerValidationService, PlayerCardSearchService playerCardSearchService) {
        this.playerValidationService = playerValidationService;
        this.playerCardSearchService = playerCardSearchService;
    }

    public boolean canPlayBuildingCard(Player opponent) {
        return playerValidationService.canPlaceBuildingCard(opponent) && !playerCardSearchService.findBuildingCardsInHand(opponent).isEmpty();
    }

    public boolean canPlayCharacterCard(Player opponent) {
        return playerValidationService.canPlaceCharacterCard(opponent) && !playerCardSearchService.findCharacterCardsInHand(opponent).isEmpty();
    }

}
