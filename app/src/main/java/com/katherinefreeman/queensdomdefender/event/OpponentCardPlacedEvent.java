package com.katherinefreeman.queensdomdefender.event;

import com.katherinefreeman.queensdomdefender.card.model.Card;

public class OpponentCardPlacedEvent {

    private Card card;

    public OpponentCardPlacedEvent(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

}
