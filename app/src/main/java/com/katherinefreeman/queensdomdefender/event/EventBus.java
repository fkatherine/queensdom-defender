package com.katherinefreeman.queensdomdefender.event;

import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.gamelog.model.GameLogItem;
import com.katherinefreeman.queensdomdefender.player.model.Player;
import com.katherinefreeman.queensdomdefender.player.model.PlayerType;

import java.util.List;

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

    public void userHandUpdated(List<Card> hand) {
        post(new UserHandUpdatedEvent(hand));
    }

    public void newTurnStarted(PlayerType playerType) {
        post(new TurnStartedEvent(playerType));
    }

    public void turnEnded() {
        post(new TurnEndedEvent());
    }

    public void playerStatusUpdated(Player player, PlayerType playerType) {
        post(new PlayerStatusUpdatedEvent(player, playerType));
    }

    public void playerCardPlacementStageStarted(PlayerType playerType) {
        post(new PlayerCardPlacementStageStartedEvent(playerType));
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
