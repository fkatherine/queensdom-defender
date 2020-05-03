package com.katherinefreeman.queensdomdefender.event;

import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.gamelog.model.GameLogItem;
import com.katherinefreeman.queensdomdefender.player.model.Player;
import com.katherinefreeman.queensdomdefender.player.model.PlayerType;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Collections;
import java.util.List;

import static com.katherinefreeman.queensdomdefender.card.model.CardType.CHARACTER;
import static com.katherinefreeman.queensdomdefender.player.model.PlayerType.HERO;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class EventBusUnitTest {

    private org.greenrobot.eventbus.EventBus eventBus = mock(org.greenrobot.eventbus.EventBus.class);

    private Object subscriber = new Object();

    private EventBus target = new EventBus(eventBus);

    @Test
    public void shouldPostLogGameEventWithGameItemOnLogGameEvent() {
        target.logGameEvent("LOG", 0);

        ArgumentCaptor<GameLogItem> logCaptor = ArgumentCaptor.forClass(GameLogItem.class);
        verify(eventBus).post(logCaptor.capture());
        assertThat(logCaptor.getValue().getText(), is("LOG"));
        assertThat(logCaptor.getValue().getTextColour(), is(0));
    }

    @Test
    public void shouldPostUserCardsUpdatedEventOnUserCardsUpdated() {
        List<Card> expectedCards = Collections.singletonList(new Card("Name", 0, 1, 2, 3, CHARACTER));

        target.userHandUpdated(expectedCards);

        ArgumentCaptor<UserHandUpdatedEvent> eventCaptor = ArgumentCaptor.forClass(UserHandUpdatedEvent.class);
        verify(eventBus).post(eventCaptor.capture());
        assertThat(eventCaptor.getValue().getUpdatedHand(), is(expectedCards));
    }

    @Test
    public void shouldPostTurnStartedEventOnNewTurnStarted() {
        PlayerType expectedPlayerType = HERO;

        target.newTurnStarted(expectedPlayerType);

        ArgumentCaptor<TurnStartedEvent> eventCaptor = ArgumentCaptor.forClass(TurnStartedEvent.class);
        verify(eventBus).post(eventCaptor.capture());
        assertThat(eventCaptor.getValue().getPlayerType(), is(expectedPlayerType));
    }

    @Test
    public void shouldPostTurnEndedEventOnTurnEnded() {
        target.turnEnded();

        verify(eventBus).post(any(TurnEndedEvent.class));
    }

    @Test
    public void shouldPostPlayerStatusUpdatedEventOnPlayerStatusUpdated() {
        Player expectedPlayer = new Player();
        PlayerType expectedPlayerType = HERO;

        target.playerStatusUpdated(expectedPlayer, expectedPlayerType);

        ArgumentCaptor<PlayerStatusUpdatedEvent> eventCaptor = ArgumentCaptor.forClass(PlayerStatusUpdatedEvent.class);
        verify(eventBus).post(eventCaptor.capture());
        assertThat(eventCaptor.getValue().getPlayer(), is(expectedPlayer));
        assertThat(eventCaptor.getValue().getPlayerType(), is(expectedPlayerType));
    }

    @Test
    public void shouldPostPlayerCardPlacementStageStartedEvent() {
        PlayerType expectedPlayerType = HERO;

        target.playerCardPlacementStageStarted(expectedPlayerType);

        ArgumentCaptor<PlayerCardPlacementStageStartedEvent> eventCaptor = ArgumentCaptor.forClass(PlayerCardPlacementStageStartedEvent.class);
        verify(eventBus).post(eventCaptor.capture());
        assertThat(eventCaptor.getValue().getPlayerType(), is(expectedPlayerType));
    }

    @Test
    public void shouldNotSubscribeToEventBusWhenSubscriberHasSubscribedBefore() {
        givenSubscriberIsRegistered();

        target.subscribe(subscriber);

        verify(eventBus, never()).register(any());
    }

    @Test
    public void shouldSubscribeToEventBusWhenSubscriberHasNotSubscribedBefore() {
        givenSubscriberIsNotRegistered();

        target.subscribe(subscriber);

        verify(eventBus).register(subscriber);
    }

    @Test
    public void shouldNotUnsubscribeWhenSubscriberHasNotSubscribedBefore() {
        givenSubscriberIsNotRegistered();

        target.unsubscribe(subscriber);

        verify(eventBus, never()).unregister(any());
    }

    @Test
    public void shouldUnsubscribeWhenSubscriberHasSubscribedBefore() {
        givenSubscriberIsRegistered();

        target.unsubscribe(subscriber);

        verify(eventBus).unregister(subscriber);
    }

    private void givenSubscriberIsRegistered() {
        given(eventBus.isRegistered(subscriber)).willReturn(true);
    }

    private void givenSubscriberIsNotRegistered() {
        given(eventBus.isRegistered(subscriber)).willReturn(false);
    }

}