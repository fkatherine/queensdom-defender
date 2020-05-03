package com.katherinefreeman.queensdomdefender.event;

import com.katherinefreeman.queensdomdefender.card.model.Card;

import java.util.List;

public class UserHandUpdatedEvent {

    private List<Card> updatedHand;

    public UserHandUpdatedEvent(List<Card> updatedHand) {
        this.updatedHand = updatedHand;
    }

    public List<Card> getUpdatedHand() {
        return updatedHand;
    }

}
