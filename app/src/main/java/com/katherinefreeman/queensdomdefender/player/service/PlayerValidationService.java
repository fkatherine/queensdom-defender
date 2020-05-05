package com.katherinefreeman.queensdomdefender.player.service;

import com.katherinefreeman.queensdomdefender.card.service.CardValidationService;
import com.katherinefreeman.queensdomdefender.player.model.Player;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.katherinefreeman.queensdomdefender.card.model.CardType.BUILDING;
import static com.katherinefreeman.queensdomdefender.card.model.CardType.CHARACTER;
import static com.katherinefreeman.queensdomdefender.config.Configuration.MAXIMUM_BUILDING_CARDS_IN_PLAY_LIMIT;
import static com.katherinefreeman.queensdomdefender.config.Configuration.MAXIMUM_CHARACTER_CARDS_IN_PLAY_LIMIT;

@Singleton
public class PlayerValidationService {

    private CardValidationService cardValidationService;

    @Inject
    public PlayerValidationService(CardValidationService cardValidationService) {
        this.cardValidationService = cardValidationService;
    }

    public boolean canDrawCardFromDeck(Player player) {
        return cardValidationService.hasAvailableHandSpace(player.getHand()) && !player.getDeck().isEmpty();
    }

    public boolean canPlaceBuildingCard(Player player) {
        return player
                .getField()
                .stream()
                .filter(card -> card.getType() == BUILDING)
                .count() < MAXIMUM_BUILDING_CARDS_IN_PLAY_LIMIT;
    }

    public boolean canPlaceCharacterCard(Player player) {
        return player
                .getField()
                .stream()
                .filter(card -> card.getType() == CHARACTER)
                .count() < MAXIMUM_CHARACTER_CARDS_IN_PLAY_LIMIT;
    }

}
