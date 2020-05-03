package com.katherinefreeman.queensdomdefender.turnstatus.view;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.event.TurnStartedEvent;
import com.katherinefreeman.queensdomdefender.player.model.PlayerType;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.katherinefreeman.queensdomdefender.player.model.PlayerType.HERO;

@Singleton
public class TurnStatusFragmentViewModel extends ViewModel {

    public ObservableField<PlayerType> currentPlayer = new ObservableField<>(HERO);
    private EventBus eventBus;

    @Inject
    public TurnStatusFragmentViewModel(EventBus eventBus) {
        this.eventBus = eventBus;

        eventBus.subscribe(this);
    }

    @Subscribe
    public void onTurnStarted(TurnStartedEvent event) {
        currentPlayer.set(event.getPlayerType());
    }

    public void onEndTurn() {
        eventBus.turnEnded();
    }

}
