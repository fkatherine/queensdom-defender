package com.katherinefreeman.queensdomdefender.event;

import com.katherinefreeman.queensdomdefender.gamelog.model.GameLogItem;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class EventBus {

    private org.greenrobot.eventbus.EventBus eventBus;

    @Inject
    public EventBus(org.greenrobot.eventbus.EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void logGameEvent(String text, int textColour) {
        post(new GameLogItem(text, textColour));
    }

    private void post(Object event) {
        eventBus.post(event);
    }

    public void subscribe(Object subscriber) {
        if (!eventBus.isRegistered(subscriber)) {
            eventBus.register(subscriber);
        }
    }

    public void unsubscribe(Object subscriber) {
        if (eventBus.isRegistered(subscriber)) {
            eventBus.unregister(subscriber);
        }
    }
}
