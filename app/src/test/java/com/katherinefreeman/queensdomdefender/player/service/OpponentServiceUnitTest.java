package com.katherinefreeman.queensdomdefender.player.service;

import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.player.model.Player;

import org.junit.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OpponentServiceUnitTest {

    private OpponentValidationService opponentValidationService = mock(OpponentValidationService.class);
    private OpponentCardService opponentCardService = mock(OpponentCardService.class);
    private EventBus eventBus = mock(EventBus.class);

    private Player opponent = new Player();

    private OpponentService target = new OpponentService(opponentValidationService, opponentCardService, eventBus);

    public OpponentServiceUnitTest() {}

    @Test
    public void shouldPlaceBuildingCardWhenOpponentIsAbleToPlayBuildingCardOnStartCardPlacementTurn() {
        given(opponentValidationService.canPlayBuildingCard(opponent)).willReturn(true);

        target.startCardPlacementTurn(opponent);

        verify(opponentCardService).placeBuildingCard(opponent);
    }

    @Test
    public void shouldPlaceBuildingCardWhenOpponentIsUnableToPlayBuildingCardAndAbleToPlayCharacterCardOnStartCardPlacementTurn() {
        given(opponentValidationService.canPlayBuildingCard(opponent)).willReturn(false);
        given(opponentValidationService.canPlayCharacterCard(opponent)).willReturn(true);

        target.startCardPlacementTurn(opponent);

        verify(opponentCardService).placeCharacterCard(opponent);
    }

    @Test
    public void shouldPostTurnEndedEventOnStartCardPlacementTurn() {
        target.startCardPlacementTurn(opponent);

        verify(eventBus).turnEnded();
    }

}
