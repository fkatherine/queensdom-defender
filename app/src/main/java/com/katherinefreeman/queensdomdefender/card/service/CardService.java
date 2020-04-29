package com.katherinefreeman.queensdomdefender.card.service;

import com.katherinefreeman.queensdomdefender.card.builder.DeckBuilder;
import com.katherinefreeman.queensdomdefender.card.model.Card;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CardService {

    private DeckBuilder deckBuilder;

    @Inject
    public CardService(DeckBuilder deckBuilder) {
        this.deckBuilder = deckBuilder;
    }

    public List<Card> buildShuffledDeck() {
        List<Card> availableDeck = deckBuilder.buildAvailableDeck();
        Collections.shuffle(availableDeck);

        return availableDeck;
    }
}
