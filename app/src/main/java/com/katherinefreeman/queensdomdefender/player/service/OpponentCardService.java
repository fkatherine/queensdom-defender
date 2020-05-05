package com.katherinefreeman.queensdomdefender.player.service;

import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.player.model.Player;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class OpponentCardService {

    private PlayerCardSearchService playerCardSearchService;
    private EventBus eventBus;

    @Inject
    public OpponentCardService(PlayerCardSearchService playerCardSearchService, EventBus eventBus) {
        this.playerCardSearchService = playerCardSearchService;
        this.eventBus = eventBus;
    }

    public void placeBuildingCard(Player opponent) {
        Card buildingCard = playerCardSearchService.findBuildingCardsInHand(opponent).get(0);
        addCardToField(opponent, buildingCard);
    }

    private void addCardToField(Player opponent, Card card) {
        opponent.getField().add(card);
        opponent.getHand().remove(card);
        eventBus.opponentCardPlaced(card);
    }

    public void placeCharacterCard(Player opponent) {
        Card characterCard = playerCardSearchService.findCharacterCardsInHand(opponent).get(0);
        addCardToField(opponent, characterCard);
    }

}
