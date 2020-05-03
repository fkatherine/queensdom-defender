package com.katherinefreeman.queensdomdefender.player.service;

import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.card.service.CardValidationService;
import com.katherinefreeman.queensdomdefender.player.model.Player;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.katherinefreeman.queensdomdefender.card.model.CardType.CHARACTER;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class PlayerValidationServiceUnitTest {

    private CardValidationService cardValidationService = mock(CardValidationService.class);

    private Player player = new Player();
    private ArrayList<Card> hand = new ArrayList<>();
    private ArrayList<Card> deck = new ArrayList<>();

    private PlayerValidationService target = new PlayerValidationService(cardValidationService);

    @Before
    public void setUp() {
        player.setHand(hand);
        player.setDeck(deck);
    }

    @Test
    public void shouldNotBeAbleToDrawCardWhenDoesNotHaveAvailableHandSpaceAndDoesNotHaveCardsLeftInDeck() {
        givenPlayerHandSpaceNotAvailable();
        givenCardsNotAvailableInPlayerDeck();

        assertThat(target.canDrawCardFromDeck(player), is(false));
    }

    @Test
    public void shouldNotBeAbleToDrawCardWhenHasAvailableHandSpaceAndDoesNotHaveCardsLeftInDeck() {
        givenPlayerHandSpaceNotAvailable();
        givenCardsAvailableInPlayerDeck();

        assertThat(target.canDrawCardFromDeck(player), is(false));
    }

    @Test
    public void shouldNotBeAbleToDrawCardWhenDoesNotHaveAvailableHandSpaceAndHasCardsLeftInDeck() {
        givenPlayerHandSpaceAvailable();
        givenCardsNotAvailableInPlayerDeck();

        assertThat(target.canDrawCardFromDeck(player), is(false));
    }

    @Test
    public void shouldBeAbleToDrawCardWhenHasAvailableHandSpaceAndHasCardsLeftInDeck() {
        givenPlayerHandSpaceAvailable();
        givenCardsAvailableInPlayerDeck();

        assertThat(target.canDrawCardFromDeck(player), is(true));
    }

    private void givenPlayerHandSpaceNotAvailable() {
        given(cardValidationService.hasAvailableHandSpace(hand)).willReturn(false);
    }

    private void givenPlayerHandSpaceAvailable() {
        given(cardValidationService.hasAvailableHandSpace(hand)).willReturn(true);
    }

    private void givenCardsNotAvailableInPlayerDeck() {
        deck.clear();
    }

    private void givenCardsAvailableInPlayerDeck() {
        deck.add(new Card("Archer", 1, 1, 1, 1, CHARACTER));
    }
}