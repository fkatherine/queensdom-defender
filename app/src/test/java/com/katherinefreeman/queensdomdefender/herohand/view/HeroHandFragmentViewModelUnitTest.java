package com.katherinefreeman.queensdomdefender.herohand.view;

import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.card.model.CardType;
import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.event.UserCardPlacedEvent;
import com.katherinefreeman.queensdomdefender.event.UserHandUpdatedEvent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HeroHandFragmentViewModelUnitTest {

    private EventBus eventBus = mock(EventBus.class);

    private List<Card> cardsDrawn = new ArrayList<>(Arrays.asList(
            new Card("Warrior", 1, 3, 3, 3, CardType.CHARACTER),
            new Card("Fisher", 1, 3, 3, 3, CardType.CHARACTER)
    ));

    private HeroHandFragmentViewModel target = new HeroHandFragmentViewModel(eventBus);

    @Test
    public void shouldSubscribeToEventBusOnInit() {
        verify(eventBus).subscribe(target);
    }

    @Test
    public void shouldUpdateHandOnUserHandUpdated() {
        UserHandUpdatedEvent event = new UserHandUpdatedEvent(cardsDrawn);

        target.onUserHandUpdated(event);

        assertThat(target.hand, is(cardsDrawn));
    }

    @Test
    public void shouldRemoveCardFromHandOnUserCardPlaced() {
        Card card = cardsDrawn.get(0);
        target.hand.addAll(cardsDrawn);

        target.onUserCardPlaced(new UserCardPlacedEvent(card));

        assertThat(target.hand.contains(card), is(false));
    }

}
