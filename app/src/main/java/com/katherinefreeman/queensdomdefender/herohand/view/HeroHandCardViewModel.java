package com.katherinefreeman.queensdomdefender.herohand.view;

import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.card.view.CardViewModel;
import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.event.TurnEndedEvent;
import com.katherinefreeman.queensdomdefender.event.UserCardPlacementStageStartedEvent;
import com.katherinefreeman.queensdomdefender.event.UserCardPlayedEvent;

import org.greenrobot.eventbus.Subscribe;

public class HeroHandCardViewModel extends CardViewModel {

    public HeroHandCardViewModel(Card card, EventBus eventBus) {
        super(card, eventBus);
    }

    @Subscribe(sticky = true)
    public void onUserCardPlacementStageStarted(UserCardPlacementStageStartedEvent event) {
        showCardOverlay.set(true);
    }

    public void onCardInteraction() {
        this.eventBus.playUserCard(card);
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
