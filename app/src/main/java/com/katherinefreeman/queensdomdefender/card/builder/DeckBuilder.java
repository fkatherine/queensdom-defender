package com.katherinefreeman.queensdomdefender.card.builder;

import com.katherinefreeman.queensdomdefender.card.model.Card;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.katherinefreeman.queensdomdefender.card.deck.Deck.availableDeck;

@Singleton
public class DeckBuilder {

    @Inject
    public DeckBuilder() {
    }

    public ArrayList<Card> buildAvailableDeck() {
        return new ArrayList<>(availableDeck);
    }

}
