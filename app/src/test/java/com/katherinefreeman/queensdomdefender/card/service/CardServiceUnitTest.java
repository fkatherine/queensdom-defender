package com.katherinefreeman.queensdomdefender.card.service;

import com.katherinefreeman.queensdomdefender.R;
import com.katherinefreeman.queensdomdefender.card.builder.DeckBuilder;
import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.player.model.Player;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.katherinefreeman.queensdomdefender.card.model.CardType.CHARACTER;
import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CardServiceUnitTest {

    private DeckBuilder deckBuilder = mock(DeckBuilder.class);

    private ArrayList<Card> expectedDeck = new ArrayList<>(Arrays.asList(
            new Card("Archer", R.drawable.character_card_09_archer, 1, 1, 1, CHARACTER),
            new Card("Fish", R.drawable.character_card_09_archer, 2, 2, 2, CHARACTER),
            new Card("Monk", R.drawable.character_card_09_archer, 3, 3, 3, CHARACTER),
            new Card("Scarecrow", R.drawable.character_card_09_archer, 4, 4, 4, CHARACTER),
            new Card("Armoured Knight", R.drawable.character_card_09_archer, 5, 5, 5, CHARACTER)
    ));
    private ArrayList<Card> expectedDeckCopy = new ArrayList<>(expectedDeck);

    private CardService target = new CardService(deckBuilder);

    @Test
    public void shouldBuildShuffledDeckOnBuildShuffledDeck() {
        given(deckBuilder.buildAvailableDeck()).willReturn(expectedDeckCopy);
        boolean testPassed = false;

        testPassed = assertThatDeckIsShuffled(testPassed);

        if (!testPassed) {
            fail();
        }
    }

    @Test
    public void shouldDrawCardsFromPlayersDeckAndPlaceIntoHandOnDrawCardsIntoHand() {
        Player player = new Player();
        player.setDeck(expectedDeckCopy);
        player.setHand(new ArrayList<>());

        target.drawCardsIntoHand(player, 5);

        assertThat(player.getHand(), is(expectedDeck));
        assertThat(player.getDeck().isEmpty(), is(true));
    }

    private boolean assertThatDeckIsShuffled(boolean testPassed) {
        for (int i = 0; i < 10; i++) {
            try {
                List<Card> shuffledCards = target.buildShuffledDeck();

                assertThat(shuffledCards.containsAll(expectedDeck), is(true));
                assertThat(shuffledCards, is(not(equalTo(expectedDeck))));
                testPassed = true;
            } catch (AssertionError error) {
                System.out.println("Was not shuffled deck");
            }
        }
        return testPassed;
    }
}