package com.katherinefreeman.queensdomdefender.player.service;

import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.player.model.Player;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static com.katherinefreeman.queensdomdefender.card.model.CardType.BUILDING;
import static com.katherinefreeman.queensdomdefender.card.model.CardType.CHARACTER;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PlayerCardSearchServiceUnitTest {

    private Player player = new Player();
    private Card buildingCard = new Card("Cathedral", 1, 1, 1, 0, BUILDING);
    private Card characterCard = new Card("Archer", 1, 1, 1, 1, CHARACTER);

    private PlayerCardSearchService target = new PlayerCardSearchService();

    @Test
    public void shouldReturnNoResultsWhenNoBuildingCardsInHandOnFindBuildingCardsInHand() {
        withCharacterCardsInHandOnly();

        assertThat(target.findBuildingCardsInHand(player).isEmpty(), is(true));
    }

    @Test
    public void shouldFindBuildingCardsInHand() {
        withCardsInHand();

        assertThat(target.findBuildingCardsInHand(player), is(singletonList(buildingCard)));
    }

    @Test
    public void shouldReturnNoResultsWhenNoCharacterCardsInHandOnFindCharacterCardsInHand() {
        withBuildingCardsInHandOnly();

        assertThat(target.findCharacterCardsInHand(player).isEmpty(), is(true));
    }

    @Test
    public void shouldFindCharacterCardsInHand() {
        withCardsInHand();

        assertThat(target.findCharacterCardsInHand(player), is(singletonList(characterCard)));
    }

    private void withBuildingCardsInHandOnly() {
        player.setHand(new ArrayList<>(singletonList(buildingCard)));
    }

    private void withCharacterCardsInHandOnly() {
        player.setHand(new ArrayList<>(singletonList(characterCard)));
    }

    private void withCardsInHand() {
        player.setHand(new ArrayList<>(Arrays.asList(buildingCard, characterCard)));
    }

}
