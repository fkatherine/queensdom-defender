package com.katherinefreeman.queensdomdefender.event;

import com.katherinefreeman.queensdomdefender.player.model.PlayerType;

public class TurnStartedEvent {

    private PlayerType playerType;

    public TurnStartedEvent(PlayerType playerType) {
        this.playerType = playerType;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

}
