package com.katherinefreeman.queensdomdefender.card.view;

import androidx.databinding.ObservableBoolean;

import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.event.TurnEndedEvent;

import org.greenrobot.eventbus.Subscribe;

public abstract class CardViewModel {

    public Card card;
    public ObservableBoolean showCardOverlay = new ObservableBoolean(false);

    protected EventBus eventBus;

    public CardViewModel(Card card, EventBus eventBus) {
        this.card = card;
        this.eventBus = eventBus;

        eventBus.subscribe(this);
    }

    public abstract void onCardInteraction();

    @Subscribe
    public void onTurnEnded(TurnEndedEvent event) {
        showCardOverlay.set(false);
    }

}
