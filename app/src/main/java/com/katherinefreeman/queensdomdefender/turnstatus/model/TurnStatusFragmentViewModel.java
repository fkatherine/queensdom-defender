package com.katherinefreeman.queensdomdefender.turnstatus.model;

import androidx.lifecycle.ViewModel;

import com.katherinefreeman.queensdomdefender.player.model.PlayerType;

import static com.katherinefreeman.queensdomdefender.player.model.PlayerType.HERO;

public class TurnStatusFragmentViewModel extends ViewModel {

    public PlayerType currentPlayerType = HERO;

}
