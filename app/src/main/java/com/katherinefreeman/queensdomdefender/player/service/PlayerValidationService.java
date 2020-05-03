package com.katherinefreeman.queensdomdefender.player.service;

import com.katherinefreeman.queensdomdefender.card.service.CardValidationService;
import com.katherinefreeman.queensdomdefender.player.model.Player;

import javax.inject.Inject;
import javax.inject.Singleton;

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

}
