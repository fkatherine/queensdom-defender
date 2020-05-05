package com.katherinefreeman.queensdomdefender.player.service;

import com.katherinefreeman.queensdomdefender.R;
import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.player.model.Player;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class UserService {

    private PlayerValidationService playerValidationService;
    private EventBus eventBus;

    @Inject
    public UserService(PlayerValidationService playerValidationService, EventBus eventBus) {
        this.playerValidationService = playerValidationService;
        this.eventBus = eventBus;
    }

    public void placeBuildingCard(Player user, Card buildingCard) {
        if (!playerValidationService.canPlaceBuildingCard(user)) {
            eventBus.logGameEvent("You cannot place any more building cards", R.color.errorTextColour);
        } else {
            addCardToField(user, buildingCard);
            eventBus.logGameEvent("You placed a building card", R.color.applicationTextColour);
        }
    }

    private void addCardToField(Player user, Card card) {
        user.getField().add(card);
        user.getHand().remove(card);
        eventBus.userCardPlaced(card);
    }

    public void placeCharacterCard(Player user, Card characterCard) {
        if (!playerValidationService.canPlaceCharacterCard(user)) {
            eventBus.logGameEvent("You cannot place any more character cards", R.color.errorTextColour);
        } else {
            addCardToField(user, characterCard);
            eventBus.logGameEvent("You placed a character card", R.color.applicationTextColour);
        }
    }

}
