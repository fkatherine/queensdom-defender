package com.katherinefreeman.queensdomdefender.card.view;

import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.event.TurnEndedEvent;

import org.junit.Test;

import static com.katherinefreeman.queensdomdefender.card.model.CardType.CHARACTER;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CardViewModelUnitTest {

    private EventBus eventBus = mock(EventBus.class);

    private Card card = new Card("Archer", 1, 1, 2, 3, CHARACTER);

    private TestCardViewModel target = new TestCardViewModel(card, eventBus);

    @Test
    public void shouldSubscribeToEventBusOnInit() {
        verify(eventBus).subscribe(target);
    }

    @Test
    public void shouldNotShowCardOverlayOnInit() {
        assertThat(target.showCardOverlay.get(), is(false));
    }

    @Test
    public void shouldNotShowOverlayOnTurnEndedEvent() {
        target.showCardOverlay.set(true);

        target.onTurnEnded(new TurnEndedEvent());

        assertThat(target.showCardOverlay.get(), is(false));
    }

    private class TestCardViewModel extends CardViewModel {

        public TestCardViewModel(Card card, EventBus eventbus) {
            super(card, eventbus);
        }

        @Override
        public void onCardInteraction() {
        }

    }

}
