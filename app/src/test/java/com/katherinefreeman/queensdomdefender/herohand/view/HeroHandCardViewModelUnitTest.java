package com.katherinefreeman.queensdomdefender.herohand.view;

import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.event.TurnEndedEvent;
import com.katherinefreeman.queensdomdefender.event.UserCardPlacementStageStartedEvent;
import com.katherinefreeman.queensdomdefender.event.UserCardPlayedEvent;

import org.junit.Test;

import static com.katherinefreeman.queensdomdefender.card.model.CardType.CHARACTER;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HeroHandCardViewModelUnitTest {

    private EventBus eventBus = mock(EventBus.class);

    private Card card = new Card("Archer", 1, 1, 2, 3, CHARACTER);

    private HeroHandCardViewModel target = new HeroHandCardViewModel(card, eventBus);

    @Test
    public void shouldSubscribeToEventBusOnInit() {
        verify(eventBus).subscribe(target);
    }

    @Test
    public void shouldNotShowCardOverlayOnInit() {
        assertThat(target.showCardOverlay.get(), is(false));
    }

    @Test
    public void shouldShowCardOverlayOnUserCardPlacementStageStartedEvent() {
        target.onUserCardPlacementStageStarted(new UserCardPlacementStageStartedEvent());

        assertThat(target.showCardOverlay.get(), is(true));
    }

    @Test
    public void shouldPostPlayUserCardEventOnPlayCard() {
        target.onPlayCard();

        verify(eventBus).playUserCard(card);
    }

    @Test
    public void shouldNotShowOverlayOnUserCardPlayedEvent() {
        target.showCardOverlay.set(true);

        target.onUserCardPlayed(new UserCardPlayedEvent(card));

        assertThat(target.showCardOverlay.get(), is(false));
    }

    @Test
    public void shouldNotShowOverlayOnTurnEndedEvent() {
        target.showCardOverlay.set(true);

        target.onTurnEnded(new TurnEndedEvent());

        assertThat(target.showCardOverlay.get(), is(false));
    }

}
