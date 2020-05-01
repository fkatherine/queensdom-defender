package com.katherinefreeman.queensdomdefender.player.service;

import com.katherinefreeman.queensdomdefender.R;
import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.card.service.CardService;
import com.katherinefreeman.queensdomdefender.player.model.Player;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static com.katherinefreeman.queensdomdefender.card.model.CardType.CHARACTER;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class PlayerServiceUnitTest {

    private CardService cardService = mock(CardService.class);

    private ArrayList<Card> expectedDeck = new ArrayList<>(Collections.singletonList(
            new Card("Archer", R.drawable.character_card_09_archer, 1, 1, 1, CHARACTER)
    ));
    private int expectedHealth = 20;
    private int expectedEnergy = 10;

    private PlayerService target = new PlayerService(cardService);

    @Test
    public void shouldCreatePlayerWithDefaultValuesOnCreateNewPlayer() {
        given(cardService.buildShuffledDeck()).willReturn(expectedDeck);

        Player player = target.createNewPlayer();

        assertThat(player.getDeck(), is(expectedDeck));
        assertThat(player.getHand().isEmpty(), is(true));
        assertThat(player.getField().isEmpty(), is(true));
        assertThat(player.getHealth(), is(expectedHealth));
        assertThat(player.getEnergy(), is(expectedEnergy));
    }
}