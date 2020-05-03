package com.katherinefreeman.queensdomdefender.event;

import com.katherinefreeman.queensdomdefender.player.model.PlayerType;

class PlayerCardPlacementStageStartedEvent {

    private PlayerType playerType;

    public PlayerCardPlacementStageStartedEvent(PlayerType playerType) {
        this.playerType = playerType;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

}
