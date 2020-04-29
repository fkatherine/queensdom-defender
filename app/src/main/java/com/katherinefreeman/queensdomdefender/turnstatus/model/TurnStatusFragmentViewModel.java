package com.katherinefreeman.queensdomdefender.turnstatus.model;

import androidx.lifecycle.ViewModel;

import com.katherinefreeman.queensdomdefender.player.model.PlayerType;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.katherinefreeman.queensdomdefender.player.model.PlayerType.HERO;

@Singleton
public class TurnStatusFragmentViewModel extends ViewModel {

    public PlayerType currentPlayerType = HERO;

    @Inject
    public TurnStatusFragmentViewModel() {
    }
}
