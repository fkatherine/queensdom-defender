package com.katherinefreeman.queensdomdefender.herohand.view;

import androidx.databinding.ObservableBoolean;

import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.event.TurnEndedEvent;
import com.katherinefreeman.queensdomdefender.event.UserCardPlacementStageStartedEvent;
import com.katherinefreeman.queensdomdefender.event.UserCardPlayedEvent;

import org.greenrobot.eventbus.Subscribe;

public class HeroHandCardViewModel {

    public Card card;
    public ObservableBoolean showCardOverlay = new ObservableBoolean();

    private EventBus eventBus;

    public HeroHandCardViewModel(Card card, EventBus eventBus) {
        this.card = card;
        this.eventBus = eventBus;

        eventBus.subscribe(this);
    }

    @Subscribe(sticky = true)
    public void onUserCardPlacementStageStarted(UserCardPlacementStageStartedEvent event) {
        showCardOverlay.set(true);
    }

    public void onPlayCard() {
        eventBus.playUserCard(card);
    }

    @Subscribe
    public void onUserCardPlayed(UserCardPlayedEvent event) {
        showCardOverlay.set(false);
    }

    @Subscribe
    public void onTurnEnded(TurnEndedEvent event) {
        showCardOverlay.set(false);
    }

}
