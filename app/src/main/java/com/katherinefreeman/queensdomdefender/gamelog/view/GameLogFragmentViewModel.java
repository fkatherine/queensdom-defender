package com.katherinefreeman.queensdomdefender.gamelog.view;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModel;

import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.gamelog.model.GameLogItem;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GameLogFragmentViewModel {

    ObservableArrayList<GameLogItem> gameLogItems = new ObservableArrayList<>();

    @Inject
    public GameLogFragmentViewModel(EventBus eventBus) {
        eventBus.subscribe(this);
    }

    @Subscribe()
    public void onLogGameEvent(GameLogItem gameLogItem) {
        gameLogItems.add(gameLogItem);
    }
}
