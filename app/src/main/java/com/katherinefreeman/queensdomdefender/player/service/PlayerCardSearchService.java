package com.katherinefreeman.queensdomdefender.player.service;

import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.player.model.Player;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.katherinefreeman.queensdomdefender.card.model.CardType.BUILDING;
import static com.katherinefreeman.queensdomdefender.card.model.CardType.CHARACTER;

@Singleton
class PlayerCardSearchService {

    @Inject
    public PlayerCardSearchService() {
    }

    public List<Card> findBuildingCardsInHand(Player player) {
        return player
                .getHand()
                .stream()
                .filter(card -> card.getType() == BUILDING)
                .collect(Collectors.toList());
    }

    public List<Card> findCharacterCardsInHand(Player player) {
        return player
                .getHand()
                .stream()
                .filter(card -> card.getType() == CHARACTER)
                .collect(Collectors.toList());
    }

}
