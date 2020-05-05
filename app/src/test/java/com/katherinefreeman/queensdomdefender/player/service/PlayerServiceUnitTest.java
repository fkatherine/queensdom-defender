package com.katherinefreeman.queensdomdefender.player.service;

import com.katherinefreeman.queensdomdefender.R;
import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.card.service.CardService;
import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.player.model.Player;

import org.junit.Test;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.Collections;

import static com.katherinefreeman.queensdomdefender.card.model.CardType.BUILDING;
import static com.katherinefreeman.queensdomdefender.card.model.CardType.CHARACTER;
import static com.katherinefreeman.queensdomdefender.config.Configuration.MAXIMUM_DRAWABLE_CARD_COUNT;
import static com.katherinefreeman.queensdomdefender.config.Configuration.MAXIMUM_PLAYER_ENERGY;
import static com.katherinefreeman.queensdomdefender.config.Configuration.MAXIMUM_PLAYER_HEALTH;
import static com.katherinefreeman.queensdomdefender.player.model.PlayerType.HERO;
import static com.katherinefreeman.queensdomdefender.player.model.PlayerType.OPPONENT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

public class PlayerServiceUnitTest {

    private CardService cardService = mock(CardService.class);
    private EventBus eventBus = mock(EventBus.class);
    private PlayerValidationService playerValidationService = mock(PlayerValidationService.class);
    private OpponentService opponentService = mock(OpponentService.class);
    private UserService userService = mock(UserService.class);

    private ArrayList<Card> expectedDeck = new ArrayList<>(Collections.singletonList(
            new Card("Archer", R.drawable.character_card_09_archer, 1, 1, 1, CHARACTER)
    ));
    private Player user = new Player();
    private ArrayList<Card> userHand = new ArrayList<>();
    private Player opponent = new Player();

    private PlayerService target = new PlayerService(cardService, eventBus, playerValidationService, opponentService, userService);

    @Test
    public void shouldCreatePlayerWithDefaultValuesOnCreateNewPlayer() {
        given(cardService.buildShuffledDeck()).willReturn(expectedDeck);

        Player player = target.createNewPlayer();

        assertThat(player.getDeck(), is(expectedDeck));
        assertThat(player.getHand().isEmpty(), is(true));
        assertThat(player.getField().isEmpty(), is(true));
        assertThat(player.getHealth(), is(MAXIMUM_PLAYER_HEALTH));
        assertThat(player.getEnergy(), is(MAXIMUM_PLAYER_ENERGY));
    }

    @Test
    public void shouldDrawConfiguredMaximumCardsForUserOnDrawFirstUserHand() {
        withUserHandAvailable();

        target.drawFirstUserHand(user);

        verifyUserCardsDrawnEvent(MAXIMUM_DRAWABLE_CARD_COUNT, "You drew 3 cards");
    }

    @Test
    public void shouldDrawConfiguredMaximumCardsForOpponentOnDrawFirstOpponentHand() {
        target.drawFirstOpponentHand(opponent);

        verifyOpponentCardsDrawnEvent(MAXIMUM_DRAWABLE_CARD_COUNT, "Opponent drew 3 cards");
    }

    @Test
    public void shouldDrawCardForUserWhenWhenAbleToDrawCardFromDeckOnStartNewUserTurn() {
        withUserHandAvailable();
        given(playerValidationService.canDrawCardFromDeck(user)).willReturn(true);

        target.startNewUserTurn(user);

        verifyUserCardsDrawnEvent(1, "You drew 1 card");
    }

    @Test
    public void shouldNotDrawCardForUserWhenUnableToDrawFromDeckOnStartNewUserTurn() {
        given(playerValidationService.canDrawCardFromDeck(user)).willReturn(false);

        target.startNewUserTurn(user);

        verifyNoInteractions(cardService);
        verify(eventBus, never()).userHandUpdated(anyList());
        verify(eventBus, never()).logGameEvent(anyString(), anyInt());
    }

    @Test
    public void shouldReplenishEnergyForUserOnStartNewUserTurn() {
        user.setEnergy(0);

        target.startNewUserTurn(user);

        assertThat(user.getEnergy(), is(MAXIMUM_PLAYER_ENERGY));
    }

    @Test
    public void shouldPostPlayerStatusUpdatedEventOnStartNewUserTurn() {
        target.startNewUserTurn(user);

        verify(eventBus).playerStatusUpdated(user, HERO);
    }

    @Test
    public void shouldPostUserCardPlacementStageStartedForHeroOnStartNewUserTurn() {
        target.startNewUserTurn(user);

        verify(eventBus).userCardPlacementStageStarted();
    }

    @Test
    public void shouldDrawCardForOpponentWhenWhenAbleToDrawCardFromDeckOnStartNewOpponentTurn() {
        given(playerValidationService.canDrawCardFromDeck(opponent)).willReturn(true);

        target.startNewOpponentTurn(opponent);

        verifyOpponentCardsDrawnEvent(1, "Opponent drew 1 card");
    }

    @Test
    public void shouldNotDrawCardForOpponentWhenUnableToDrawFromDeckOnStartNewOpponentTurn() {
        given(playerValidationService.canDrawCardFromDeck(opponent)).willReturn(false);

        target.startNewOpponentTurn(opponent);

        verifyNoInteractions(cardService);
        verify(eventBus, never()).logGameEvent(anyString(), anyInt());
    }

    @Test
    public void shouldReplenishEnergyForOpponentOnStartNewOpponentTurn() {
        opponent.setEnergy(0);

        target.startNewOpponentTurn(opponent);

        assertThat(opponent.getEnergy(), is(MAXIMUM_PLAYER_ENERGY));
    }

    @Test
    public void shouldPostPlayerStatusUpdatedEventOnStartNewOpponentTurn() {
        target.startNewOpponentTurn(opponent);

        verify(eventBus).playerStatusUpdated(opponent, OPPONENT);
    }

    @Test
    public void shouldStartOpponentCardPlacementTurnOnStartNewOpponentTurn() {
        target.startNewOpponentTurn(opponent);

        verify(opponentService).startCardPlacementTurn(opponent);
    }

    @Test
    public void shouldPlaceUserBuildingCardWhenCardTypeIsBuildingOnPlayUserCard() {
        Card buildingCard = new Card("Cathedral", 1, 1, 1, 0, BUILDING);

        target.playUserCard(user, buildingCard);

        verify(userService).placeBuildingCard(user, buildingCard);
    }

    @Test
    public void shouldPlaceUserCharacterCardWhenCardTypeIsCharacterOnPlayUserCard() {
        Card characterCard = new Card("Archer", 1, 1, 2, 3, CHARACTER);

        target.playUserCard(user, characterCard);

        verify(userService).placeCharacterCard(user, characterCard);
    }

    private void withUserHandAvailable() {
        user.setHand(userHand);
    }

    private void verifyUserCardsDrawnEvent(int expectedCardCount, String expectedLogMessage) {
        InOrder inOrder = inOrder(cardService, eventBus);
        inOrder.verify(cardService).drawCardsIntoHand(user, expectedCardCount);
        inOrder.verify(eventBus).userHandUpdated(userHand);
        inOrder.verify(eventBus).logGameEvent(expectedLogMessage, R.color.applicationTextColour);
    }

    private void verifyOpponentCardsDrawnEvent(int expectedCardCount, String expectedLogMessage) {
        InOrder inOrder = inOrder(cardService, eventBus);
        inOrder.verify(cardService).drawCardsIntoHand(opponent, expectedCardCount);
        inOrder.verify(eventBus).logGameEvent(expectedLogMessage, R.color.applicationTextColour);
    }

}
