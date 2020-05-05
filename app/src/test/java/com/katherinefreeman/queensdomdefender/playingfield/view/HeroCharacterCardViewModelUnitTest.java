package com.katherinefreeman.queensdomdefender.playingfield.view;

import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.event.UserCardPlacedEvent;

import org.junit.Test;

import static com.katherinefreeman.queensdomdefender.card.model.CardType.CHARACTER;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class HeroCharacterCardViewModelUnitTest {

    private EventBus eventBus = mock(EventBus.class);

    private Card card = new Card("Archer", 1, 1, 2, 3, CHARACTER);

    private HeroCharacterCardViewModel target = new HeroCharacterCardViewModel(card, eventBus);

    @Test
    public void shouldShowCardOverlayOnUserCardPlaced() {
        target.onUserCardPlaced(new UserCardPlacedEvent(card));

        assertThat(target.showCardOverlay.get(), is(true));
    }

}
