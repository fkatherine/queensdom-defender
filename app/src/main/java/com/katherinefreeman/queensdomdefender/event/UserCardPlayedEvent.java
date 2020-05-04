package com.katherinefreeman.queensdomdefender.event;

import com.katherinefreeman.queensdomdefender.card.model.Card;

public class UserCardPlayedEvent {

    private Card card;

    public UserCardPlayedEvent(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

}
