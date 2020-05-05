package com.katherinefreeman.queensdomdefender.player.service;

import com.katherinefreeman.queensdomdefender.R;
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

public class UserServiceUnitTest {

    private PlayerValidationService playerValidationService = mock(PlayerValidationService.class);
    private EventBus eventBus = mock(EventBus.class);

    private Player user = new Player();
    private Card buildingCard = new Card("Cathedral", 1, 2, 2, 0, BUILDING);
    private Card characterCard = new Card("Archer", 1, 2, 2, 2, CHARACTER);

    private UserService target = new UserService(playerValidationService, eventBus);

    @Test
    public void shouldPostGameLogEventWhenBuildingCardSpaceNotAvailableOnPlaceBuildingCard() {
        given(playerValidationService.canPlaceBuildingCard(user)).willReturn(false);

        target.placeBuildingCard(user, buildingCard);

        verify(eventBus).logGameEvent("You cannot place any more building cards", R.color.errorTextColour);
    }

    @Test
    public void shouldAddBuildingCardToUserFieldAndRemoveFromHandWhenBuildingCardSpaceAvailableOnPlaceBuildingCard() {
        givenUserCardPlaceBuildingCard();
        givenUserFieldAvailable();
        givenUserHandAvailableWithCards(singletonList(buildingCard));

        target.placeBuildingCard(user, buildingCard);

        assertThat(user.getField().contains(buildingCard), is(true));
        assertThat(user.getHand().contains(buildingCard), is(false));

    }

    @Test
    public void shouldPostUserCardPlacedEventWhenBuildingCardSpaceAvailableOnPlaceBuildingCard() {
        givenUserCardPlaceBuildingCard();
        givenUserFieldAvailable();
        givenUserHandAvailable();

        target.placeBuildingCard(user, buildingCard);

        verify(eventBus).userCardPlaced(buildingCard);
    }

    @Test
    public void shouldPostGameLogEventWhenBuildingCardSpaceAvailableOnPlaceBuildingCard() {
        givenUserCardPlaceBuildingCard();
        givenUserFieldAvailable();
        givenUserHandAvailable();

        target.placeBuildingCard(user, buildingCard);

        verify(eventBus).logGameEvent("You placed a building card", R.color.applicationTextColour);
    }

    @Test
    public void shouldPostGameLogEventWhenCharacterCardSpaceNotAvailableOnPlaceCharacterCard() {
        given(playerValidationService.canPlaceCharacterCard(user)).willReturn(false);

        target.placeCharacterCard(user, characterCard);

        verify(eventBus).logGameEvent("You cannot place any more character cards", R.color.errorTextColour);
    }

    @Test
    public void shouldAddCharacterCardToUserFieldAndRemoveFromHandWhenCharacterCardSpaceAvailableOnPlaceCharacterCard() {
        givenUserCardPlaceCharacterCard();
        givenUserFieldAvailable();
        givenUserHandAvailableWithCards(singletonList(characterCard));

        target.placeCharacterCard(user, characterCard);

        assertThat(user.getField().contains(characterCard), is(true));
        assertThat(user.getHand().contains(characterCard), is(false));
    }

    @Test
    public void shouldPostUserCardPlacedEventWhenCharacterCardSpaceAvailableOnPlaceCharacterCard() {
        givenUserCardPlaceCharacterCard();
        givenUserFieldAvailable();
        givenUserHandAvailable();

        target.placeCharacterCard(user, characterCard);

        verify(eventBus).userCardPlaced(characterCard);
    }

    @Test
    public void shouldPostGameLogEventWhenCharacterCardSpaceAvailableOnPlaceCharacterCard() {
        givenUserCardPlaceCharacterCard();
        givenUserFieldAvailable();
        givenUserHandAvailable();

        target.placeCharacterCard(user, characterCard);

        verify(eventBus).logGameEvent("You placed a character card", R.color.applicationTextColour);
    }

    private void givenUserCardPlaceBuildingCard() {
        given(playerValidationService.canPlaceBuildingCard(user)).willReturn(true);
    }

    private void givenUserCardPlaceCharacterCard() {
        given(playerValidationService.canPlaceCharacterCard(user)).willReturn(true);
    }

    private void givenUserFieldAvailable() {
        user.setField(new ArrayList<>());
    }

    private void givenUserHandAvailable() {
        user.setHand(new ArrayList<>());
    }

    private void givenUserHandAvailableWithCards(List<Card> cards) {
        user.setHand(new ArrayList<>(cards));
    }

}
