package com.katherinefreeman.queensdomdefender.card.service;

import com.katherinefreeman.queensdomdefender.card.model.Card;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static com.katherinefreeman.queensdomdefender.card.model.CardType.CHARACTER;
import static com.katherinefreeman.queensdomdefender.config.Configuration.MAXIMUM_CARDS_IN_HAND_LIMIT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CardValidationServiceUnitTest {

    private CardValidationService target = new CardValidationService();

    @Test
    public void shouldNotHaveAvailableHandSpaceWhenHandSizeIsEqualToMaximumCardsInHandLimit() {
        List<Card> hand = buildHand(MAXIMUM_CARDS_IN_HAND_LIMIT);

        assertThat(target.hasAvailableHandSpace(hand), is(false));
    }

    @Test
    public void shouldNotHaveAvailableHandSpaceWhenHandSizeIsMoreThanMaximumCardsInHandLimit() {
        List<Card> hand = buildHand(MAXIMUM_CARDS_IN_HAND_LIMIT + 1);

        assertThat(target.hasAvailableHandSpace(hand), is(false));
    }

    @Test
    public void shouldHaveAvailableHandSpaceWhenHandSizeIsLessThanMaximumCardsInHandLimit() {
        List<Card> hand = buildHand(MAXIMUM_CARDS_IN_HAND_LIMIT - 1);

        assertThat(target.hasAvailableHandSpace(hand), is(true));
    }

    private List<Card> buildHand(int size) {
        Card card = new Card("Archer", 2, 2, 1, 2, CHARACTER);
        return Collections.nCopies(size, card);
    }

}