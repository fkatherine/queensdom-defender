package com.katherinefreeman.queensdomdefender.player.service;

import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.player.model.Player;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class OpponentService {

    private EventBus eventBus;

    @Inject
    public OpponentService(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void startCardPlacementTurn(Player opponent) {
        eventBus.turnEnded();
    }

}
