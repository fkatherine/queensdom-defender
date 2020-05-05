package com.katherinefreeman.queensdomdefender.event;

import com.katherinefreeman.queensdomdefender.card.model.Card;

public class UserCardPlacedEvent {

    private Card card;

    public UserCardPlacedEvent(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

}
