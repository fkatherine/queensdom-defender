package com.katherinefreeman.queensdomdefender.playingfield.view;

import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.card.view.CardViewModel;
import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.event.UserCardPlacedEvent;

import org.greenrobot.eventbus.Subscribe;

public class HeroCharacterCardViewModel extends CardViewModel {

    public HeroCharacterCardViewModel(Card card, EventBus eventbus) {
        super(card, eventbus);
    }

    @Override
    public void onCardInteraction() {
    }

    @Subscribe(sticky = true)
    public void onUserCardPlaced(UserCardPlacedEvent event) {
        showCardOverlay.set(true);
    }

}
