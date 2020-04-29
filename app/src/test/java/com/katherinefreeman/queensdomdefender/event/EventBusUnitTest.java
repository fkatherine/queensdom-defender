package com.katherinefreeman.queensdomdefender.event;

import com.katherinefreeman.queensdomdefender.gamelog.model.GameLogItem;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

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

    @Test
    public void shouldPostLogGameEventWithGameItemOnLogGameEvent() {
        target.logGameEvent("LOG", 0);

        ArgumentCaptor<GameLogItem> logCaptor = ArgumentCaptor.forClass(GameLogItem.class);
        verify(eventBus).post(logCaptor.capture());
        assertThat(logCaptor.getValue().getText(), is("LOG"));
        assertThat(logCaptor.getValue().getTextColour(), is(0));
    }

    private void givenSubscriberIsRegistered() {
        given(eventBus.isRegistered(subscriber)).willReturn(true);
    }

    private void givenSubscriberIsNotRegistered() {
        given(eventBus.isRegistered(subscriber)).willReturn(false);
    }

}