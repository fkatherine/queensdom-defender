package com.katherinefreeman.queensdomdefender.playingfield.view;

import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.card.view.CardViewModel;
import com.katherinefreeman.queensdomdefender.event.EventBus;

public class OpponentCharacterCardViewModel extends CardViewModel {

    public OpponentCharacterCardViewModel(Card card, EventBus eventbus) {
        super(card, eventbus);
    }

    @Override
    public void onCardInteraction() {
    }

}
