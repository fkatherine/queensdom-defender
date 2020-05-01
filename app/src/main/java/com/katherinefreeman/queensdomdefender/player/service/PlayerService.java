package com.katherinefreeman.queensdomdefender.player.service;

import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.card.service.CardService;
import com.katherinefreeman.queensdomdefender.player.model.Player;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PlayerService {

    private CardService cardService;

    @Inject
    public PlayerService(CardService cardService) {
        this.cardService = cardService;
    }

    public Player createNewPlayer() {
        ArrayList<Card> deck = cardService.buildShuffledDeck();

        Player player = new Player();
        player.setDeck(deck);
        player.setHand(new ArrayList<>());
        player.setField(new ArrayList<>());
        player.setHealth(20);
        player.setEnergy(10);
        return player;
    }

}
