package com.katherinefreeman.queensdomdefender.player.service;

import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.player.model.Player;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static com.katherinefreeman.queensdomdefender.card.model.CardType.BUILDING;
import static com.katherinefreeman.queensdomdefender.card.model.CardType.CHARACTER;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class OpponentValidationServiceUnitTest {

    private PlayerValidationService playerValidationService = mock(PlayerValidationService.class);
    private PlayerCardSearchService playerCardSearchService = mock(PlayerCardSearchService.class);

    private Player opponent = new Player();
    private Card buildingCard = new Card("Cathedral", 1, 1, 1, 0, BUILDING);
    private List<Card> buildingCardsFound = Collections.singletonList(buildingCard);
    private Card characterCard = new Card("Cathedral", 1, 1, 1, 0, CHARACTER);
    private List<Card> characterCardsFound = Collections.singletonList(characterCard);


    private OpponentValidationService target = new OpponentValidationService(playerValidationService, playerCardSearchService);

    @Test
    public void shouldNotBeAbleToPlayBuildingCardWhenNoBuildingCardSpaceAvailableAndNoBuildingCardInHand() {
        givenNoBuildingCardSpaceAvailable();
        givenNoBuildingCardsInHand();

        assertThat(target.canPlayBuildingCard(opponent), is(false));
    }

    @Test
    public void shouldNotBeAbleToPlayBuildingCardWhenBuildingCardSpaceAvailableAndNoBuildingCardInHand() {
        givenBuildingCardSpaceAvailable();
        givenNoBuildingCardsInHand();

        assertThat(target.canPlayBuildingCard(opponent), is(false));
    }

    @Test
    public void shouldNotBeAbleToPlayBuildingCardWhenNoBuildingCardSpaceAvailableAndBuildingCardInHand() {
        givenNoBuildingCardSpaceAvailable();
        givenBuildingCardsInHand();

        assertThat(target.canPlayBuildingCard(opponent), is(false));
    }

    @Test
    public void shouldBeAbleToPlayBuildingCardWhenBuildingCardSpaceAvailableAndBuildingCardInHand() {
        givenBuildingCardSpaceAvailable();
        givenBuildingCardsInHand();

        assertThat(target.canPlayBuildingCard(opponent), is(true));
    }

    @Test
    public void shouldNotBeAbleToPlayCharacterCardWhenNoCharacterCardSpaceAvailableAndNoCharacterCardInHand() {
        givenNoCharacterCardSpaceAvailable();
        givenNoCharacterCardsInHand();

        assertThat(target.canPlayCharacterCard(opponent), is(false));
    }

    @Test
    public void shouldNotBeAbleToPlayCharacterCardWhenCharacterCardSpaceAvailableAndNoCharacterCardInHand() {
        givenCharacterCardSpaceAvailable();
        givenNoCharacterCardsInHand();

        assertThat(target.canPlayCharacterCard(opponent), is(false));
    }

    @Test
    public void shouldNotBeAbleToPlayCharacterCardWhenNoCharacterCardSpaceAvailableAndCharacterCardInHand() {
        givenNoCharacterCardSpaceAvailable();
        givenCharacterCardsInHand();

        assertThat(target.canPlayCharacterCard(opponent), is(false));
    }

    @Test
    public void shouldBeAbleToPlayCharacterCardWhenCharacterCardSpaceAvailableAndCharacterCardInHand() {
        givenCharacterCardSpaceAvailable();
        givenCharacterCardsInHand();

        assertThat(target.canPlayCharacterCard(opponent), is(true));
    }

    private void givenNoBuildingCardSpaceAvailable() {
        given(playerValidationService.canPlaceBuildingCard(opponent)).willReturn(false);
    }

    private void givenBuildingCardSpaceAvailable() {
        given(playerValidationService.canPlaceBuildingCard(opponent)).willReturn(true);
    }

    private void givenNoBuildingCardsInHand() {
        given(playerCardSearchService.findBuildingCardsInHand(opponent)).willReturn(emptyList());
    }

    private void givenBuildingCardsInHand() {
        given(playerCardSearchService.findBuildingCardsInHand(opponent)).willReturn(buildingCardsFound);
    }

    private void givenNoCharacterCardSpaceAvailable() {
        given(playerValidationService.canPlaceCharacterCard(opponent)).willReturn(false);
    }

    private void givenCharacterCardSpaceAvailable() {
        given(playerValidationService.canPlaceCharacterCard(opponent)).willReturn(true);
    }

    private void givenNoCharacterCardsInHand() {
        given(playerCardSearchService.findCharacterCardsInHand(opponent)).willReturn(emptyList());
    }

    private void givenCharacterCardsInHand() {
        given(playerCardSearchService.findCharacterCardsInHand(opponent)).willReturn(characterCardsFound);
    }

}
