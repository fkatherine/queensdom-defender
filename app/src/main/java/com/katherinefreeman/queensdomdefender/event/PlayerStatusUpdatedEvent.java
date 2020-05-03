package com.katherinefreeman.queensdomdefender.event;

import com.katherinefreeman.queensdomdefender.player.model.Player;
import com.katherinefreeman.queensdomdefender.player.model.PlayerType;

class PlayerStatusUpdatedEvent {

    private Player player;
    private PlayerType playerType;

    public PlayerStatusUpdatedEvent(Player player, PlayerType playerType) {
        this.player = player;
        this.playerType = playerType;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }


}
