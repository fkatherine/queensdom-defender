package com.katherinefreeman.queensdomdefender.herohand.view;

import androidx.databinding.ObservableArrayList;

import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.event.UserHandUpdatedEvent;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class HeroHandFragmentViewModel {

    ObservableArrayList<Card> hand = new ObservableArrayList<>();

    @Inject
    public HeroHandFragmentViewModel(EventBus eventBus) {
        eventBus.subscribe(this);
    }

    @Subscribe
    public void onUserHandUpdated(UserHandUpdatedEvent event) {
        hand.clear();
        hand.addAll(event.getUpdatedHand());
    }

}
