package com.katherinefreeman.queensdomdefender.card.service;

import com.katherinefreeman.queensdomdefender.card.builder.DeckBuilder;
import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.player.model.Player;

import java.util.ArrayList;
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

    public ArrayList<Card> buildShuffledDeck() {
        ArrayList<Card> availableDeck = deckBuilder.buildAvailableDeck();
        Collections.shuffle(availableDeck);

        return availableDeck;
    }

    public void drawCardsIntoHand(Player player, int cardCount) {
        ArrayList<Card> deck = player.getDeck();
        ArrayList<Card> newHand = player.getHand();

        List<Card> drawnCards = deck.subList(0, cardCount);
        newHand.addAll(drawnCards);
        player.setHand(newHand);

        deck.removeAll(drawnCards);
    }
}
