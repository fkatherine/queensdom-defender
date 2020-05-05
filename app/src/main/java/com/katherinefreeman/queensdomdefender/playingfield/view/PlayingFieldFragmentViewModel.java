package com.katherinefreeman.queensdomdefender.playingfield.view;

import androidx.databinding.ObservableArrayList;

import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.event.OpponentCardPlacedEvent;
import com.katherinefreeman.queensdomdefender.event.UserCardPlacedEvent;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.katherinefreeman.queensdomdefender.card.model.CardType.BUILDING;

@Singleton
public class PlayingFieldFragmentViewModel {

    ObservableArrayList<Card> userBuildingCards = new ObservableArrayList<>();
    ObservableArrayList<Card> userCharacterCards = new ObservableArrayList<>();

    ObservableArrayList<Card> opponentBuildingCards = new ObservableArrayList<>();
    ObservableArrayList<Card> opponentCharacterCards = new ObservableArrayList<>();

    @Inject
    public PlayingFieldFragmentViewModel(EventBus eventBus) {
        eventBus.subscribe(this);
    }

    @Subscribe
    public void onUserCardPlaced(UserCardPlacedEvent event) {
        Card card = event.getCard();
        if (card.getType() == BUILDING) {
            userBuildingCards.add(card);
        } else {
            userCharacterCards.add(card);
        }
    }

    @Subscribe
    public void onOpponentCardPlaced(OpponentCardPlacedEvent event) {
        Card card = event.getCard();
        if (card.getType() == BUILDING) {
            opponentBuildingCards.add(card);
        } else {
            opponentCharacterCards.add(card);
        }
    }

}
