package com.katherinefreeman.queensdomdefender.player.service;

import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.card.model.CardType;
import com.katherinefreeman.queensdomdefender.card.service.CardValidationService;
import com.katherinefreeman.queensdomdefender.player.model.Player;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.katherinefreeman.queensdomdefender.card.model.CardType.BUILDING;
import static com.katherinefreeman.queensdomdefender.card.model.CardType.CHARACTER;
import static com.katherinefreeman.queensdomdefender.config.Configuration.MAXIMUM_BUILDING_CARDS_IN_PLAY_LIMIT;
import static com.katherinefreeman.queensdomdefender.config.Configuration.MAXIMUM_CHARACTER_CARDS_IN_PLAY_LIMIT;
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

    @Test
    public void shouldNotAllowBuildingCardPlacementWhenPlayerHasMaximumBuildingCardLimit() {
        buildField(BUILDING, MAXIMUM_BUILDING_CARDS_IN_PLAY_LIMIT);

        assertThat(target.canPlaceBuildingCard(player), is(false));
    }

    @Test
    public void shouldAllowBuildingCardPlacementWhenPlayerDoesNotHaveMaximumBuildingCardLimit() {
        buildField(BUILDING, MAXIMUM_BUILDING_CARDS_IN_PLAY_LIMIT - 1);

        assertThat(target.canPlaceBuildingCard(player), is(true));
    }

    @Test
    public void shouldNotAllowCharacterCardPlacementWhenPlayerHasMaximumCharacterCardLimit() {
        buildField(CHARACTER, MAXIMUM_CHARACTER_CARDS_IN_PLAY_LIMIT);

        assertThat(target.canPlaceCharacterCard(player), is(false));
    }

    @Test
    public void shouldAllowCharacterCardPlacementWhenPlayerDoesNotHaveMaximumCharacterCardLimit() {
        buildField(CHARACTER, MAXIMUM_CHARACTER_CARDS_IN_PLAY_LIMIT - 1);

        assertThat(target.canPlaceCharacterCard(player), is(true));
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

    private void buildField(CardType type, int size) {
        Card card = new Card("name", 2, 2, 1, 2, type);
        List<Card> hand = Collections.nCopies(size, card);
        player.setField(new ArrayList<>(hand));
    }

}
