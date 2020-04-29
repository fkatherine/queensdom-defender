package com.katherinefreeman.queensdomdefender.card.builder;

import com.katherinefreeman.queensdomdefender.R;
import com.katherinefreeman.queensdomdefender.card.model.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.katherinefreeman.queensdomdefender.card.model.CardType.CHARACTER;

@Singleton
public class DeckBuilder {

    private List<Card> availableDeck = Arrays.asList(
            new Card("Archer", R.drawable.character_card_09_archer, 1, 1, 1, CHARACTER),
            new Card("Fish", R.drawable.character_card_09_archer, 2, 2, 2, CHARACTER),
            new Card("Monk", R.drawable.character_card_09_archer, 3, 3, 3, CHARACTER),
            new Card("Scarecrow", R.drawable.character_card_09_archer, 4, 4, 4, CHARACTER),
            new Card("Armoured Knight", R.drawable.character_card_09_archer, 5, 5, 5, CHARACTER)
    );

    @Inject
    public DeckBuilder() {

    }

    public List<Card> buildAvailableDeck() {
        return new ArrayList<>(availableDeck);
    }
}
