package com.katherinefreeman.queensdomdefender.player.service;

import com.katherinefreeman.queensdomdefender.R;
import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.card.service.CardService;
import com.katherinefreeman.queensdomdefender.event.EventBus;
import com.katherinefreeman.queensdomdefender.player.model.Player;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.katherinefreeman.queensdomdefender.card.model.CardType.BUILDING;
import static com.katherinefreeman.queensdomdefender.config.Configuration.MAXIMUM_DRAWABLE_CARD_COUNT;
import static com.katherinefreeman.queensdomdefender.config.Configuration.MAXIMUM_PLAYER_ENERGY;
import static com.katherinefreeman.queensdomdefender.config.Configuration.MAXIMUM_PLAYER_HEALTH;
import static com.katherinefreeman.queensdomdefender.player.model.PlayerType.HERO;
import static com.katherinefreeman.queensdomdefender.player.model.PlayerType.OPPONENT;

@Singleton
public class PlayerService {

    private CardService cardService;
    private EventBus eventBus;
    private PlayerValidationService playerValidationService;
    private OpponentService opponentService;
    private UserService userService;

    @Inject
    public PlayerService(CardService cardService,
                         EventBus eventBus,
                         PlayerValidationService playerValidationService,
                         OpponentService opponentService,
                         UserService userService) {
        this.cardService = cardService;
        this.eventBus = eventBus;
        this.playerValidationService = playerValidationService;
        this.opponentService = opponentService;
        this.userService = userService;
    }

    public Player createNewPlayer() {
        ArrayList<Card> deck = cardService.buildShuffledDeck();

        Player player = new Player();
        player.setDeck(deck);
        player.setHand(new ArrayList<>());
        player.setField(new ArrayList<>());
        player.setHealth(MAXIMUM_PLAYER_HEALTH);
        player.setEnergy(MAXIMUM_PLAYER_ENERGY);
        return player;
    }

    public void drawFirstUserHand(Player user) {
        drawCardsForUser(user, MAXIMUM_DRAWABLE_CARD_COUNT);
        eventBus.logGameEvent(String.format("You drew %d cards", MAXIMUM_DRAWABLE_CARD_COUNT), R.color.applicationTextColour);
    }

    private void drawCardsForUser(Player user, int count) {
        cardService.drawCardsIntoHand(user, count);
        eventBus.userHandUpdated(user.getHand());
    }

    public void drawFirstOpponentHand(Player opponent) {
        cardService.drawCardsIntoHand(opponent, MAXIMUM_DRAWABLE_CARD_COUNT);
        eventBus.logGameEvent(String.format("Opponent drew %d cards", MAXIMUM_DRAWABLE_CARD_COUNT), R.color.applicationTextColour);
    }

    public void startNewUserTurn(Player user) {
        if (playerValidationService.canDrawCardFromDeck(user)) {
            drawCardsForUser(user, 1);
            eventBus.logGameEvent("You drew 1 card", R.color.applicationTextColour);
        }

        user.setEnergy(MAXIMUM_PLAYER_ENERGY);
        eventBus.playerStatusUpdated(user, HERO);

        eventBus.userCardPlacementStageStarted();
    }

    public void startNewOpponentTurn(Player opponent) {
        if (playerValidationService.canDrawCardFromDeck(opponent)) {
            cardService.drawCardsIntoHand(opponent, 1);
            eventBus.logGameEvent("Opponent drew 1 card", R.color.applicationTextColour);
        }

        opponent.setEnergy(MAXIMUM_PLAYER_ENERGY);
        eventBus.playerStatusUpdated(opponent, OPPONENT);

        opponentService.startCardPlacementTurn(opponent);
    }

    public void playUserCard(Player user, Card card) {
        if (card.getType() == BUILDING) {
            userService.placeBuildingCard(user, card);
        } else {
            userService.placeCharacterCard(user, card);
        }
    }

}
