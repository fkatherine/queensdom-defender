package com.katherinefreeman.queensdomdefender.card.service;

import com.katherinefreeman.queensdomdefender.card.model.Card;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.katherinefreeman.queensdomdefender.config.Configuration.MAXIMUM_CARDS_IN_HAND_LIMIT;

@Singleton
public class CardValidationService {

    @Inject
    public CardValidationService() {
    }

    public boolean hasAvailableHandSpace(List<Card> hand) {
        return hand.size() < MAXIMUM_CARDS_IN_HAND_LIMIT;
    }

}
