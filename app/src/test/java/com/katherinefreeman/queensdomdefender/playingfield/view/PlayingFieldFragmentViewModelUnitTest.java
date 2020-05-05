package com.katherinefreeman.queensdomdefender.playingfield.view;

import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.event.OpponentCardPlacedEvent;
import com.katherinefreeman.queensdomdefender.event.UserCardPlacedEvent;

import org.junit.Test;

import static com.katherinefreeman.queensdomdefender.card.model.CardType.BUILDING;
import static com.katherinefreeman.queensdomdefender.card.model.CardType.CHARACTER;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PlayingFieldFragmentViewModelUnitTest {

    private EventBus eventBus = mock(EventBus.class);

    private Card buildingCard = new Card("Cathedral", 2, 4, 2, 0, BUILDING);
    private Card characterCard = new Card("Archer", 2, 4, 2, 2, CHARACTER);

    private PlayingFieldFragmentViewModel target = new PlayingFieldFragmentViewModel(eventBus);


    @Test
    public void shouldSubscribeToEventBusOnInit() {
        verify(eventBus).subscribe(target);
    }

    @Test
    public void shouldUpdateUserBuildingCardsWhenPlacedCardIsBuildingCardTypeOnUserCardPlaced() {
        target.onUserCardPlaced(new UserCardPlacedEvent(buildingCard));

        assertThat(target.userBuildingCards.contains(buildingCard), is(true));
    }

    @Test
    public void shouldUpdateUserCharacterCardsWhenPlacedCardIsCharacterCardTypeOnUserCardPlaced() {
        target.onUserCardPlaced(new UserCardPlacedEvent(characterCard));

        assertThat(target.userCharacterCards.contains(characterCard), is(true));
    }

    @Test
    public void shouldUpdateOpponentBuildingCardsWhenPlacedCardIsBuildingCardTypeOnOpponentCardPlaced() {
        target.onOpponentCardPlaced(new OpponentCardPlacedEvent(buildingCard));

        assertThat(target.opponentBuildingCards.contains(buildingCard), is(true));
    }

    @Test
    public void shouldUpdateOpponentCharacterCardsWhenPlacedCardIsCharacterCardTypeOnOpponentCardPlaced() {
        target.onOpponentCardPlaced(new OpponentCardPlacedEvent(characterCard));

        assertThat(target.opponentCharacterCards.contains(characterCard), is(true));
    }

}
