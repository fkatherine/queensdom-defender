package com.katherinefreeman.queensdomdefender.player.service;

import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.player.model.Player;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.katherinefreeman.queensdomdefender.card.model.CardType.BUILDING;
import static com.katherinefreeman.queensdomdefender.card.model.CardType.CHARACTER;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OpponentCardServiceUnitTest {

    private PlayerCardSearchService playerCardSearchService = mock(PlayerCardSearchService.class);
    private EventBus eventBus = mock(EventBus.class);

    private Player opponent = new Player();
    private Card buildingCard = new Card("Cathedral", 1, 1, 1, 0, BUILDING);
    private Card characterCard = new Card("Archer", 1, 1, 1, 1, CHARACTER);

    private OpponentCardService target = new OpponentCardService(playerCardSearchService, eventBus);

    @Test
    public void shouldAddFirstBuildingCardInHandToOpponentFieldAndRemoveFromHandOnPlaceBuildingCard() {
        givenBuildingCardsFoundInHand();
        givenOpponentFieldAvailable();
        givenOpponentHandAvailableWithCards(singletonList(buildingCard));

        target.placeBuildingCard(opponent);

        assertThat(opponent.getField().contains(buildingCard), is(true));
        assertThat(opponent.getHand().contains(buildingCard), is(false));
    }

    @Test
    public void shouldPostOpponentCardPlacedEventForFirstBuildingCardInHandOnPlaceBuildingCard() {
        givenBuildingCardsFoundInHand();
        givenOpponentFieldAvailable();
        givenOpponentHandAvailableWithCards(singletonList(buildingCard));

        target.placeBuildingCard(opponent);

        verify(eventBus).opponentCardPlaced(buildingCard);
    }

    @Test
    public void shouldAddFirstCharacterCardInHandToOpponentFieldAndRemoveFromHandOnPlaceCharacterCard() {
        givenCharacterCardsFoundInHand();
        givenOpponentFieldAvailable();
        givenOpponentHandAvailableWithCards(singletonList(characterCard));

        target.placeCharacterCard(opponent);

        assertThat(opponent.getField().contains(characterCard), is(true));
        assertThat(opponent.getHand().contains(characterCard), is(false));
    }


    @Test
    public void shouldPostOpponentCardPlacedEventForFirstCharacterCardInHandOnPlaceCharacterCard() {
        givenCharacterCardsFoundInHand();
        givenOpponentFieldAvailable();
        givenOpponentHandAvailableWithCards(singletonList(characterCard));

        target.placeCharacterCard(opponent);

        verify(eventBus).opponentCardPlaced(characterCard);
    }

    private void givenBuildingCardsFoundInHand() {
        given(playerCardSearchService.findBuildingCardsInHand(opponent)).willReturn(singletonList(buildingCard));
    }

    private void givenCharacterCardsFoundInHand() {
        given(playerCardSearchService.findCharacterCardsInHand(opponent)).willReturn(singletonList(characterCard));
    }

    private void givenOpponentFieldAvailable() {
        opponent.setField(new ArrayList<>());
    }

    private void givenOpponentHandAvailableWithCards(List<Card> cards) {
        opponent.setHand(new ArrayList<>(cards));
    }

}
