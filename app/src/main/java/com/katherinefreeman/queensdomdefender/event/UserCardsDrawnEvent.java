package com.katherinefreeman.queensdomdefender.event;

import com.katherinefreeman.queensdomdefender.card.model.Card;

import java.util.List;

public class UserCardsDrawnEvent {

    private List<Card> cardsDrawn;

    public UserCardsDrawnEvent(List<Card> cardsDrawn) {
        this.cardsDrawn = cardsDrawn;
    }

    public List<Card> getCardsDrawn() {
        return cardsDrawn;
    }
}
