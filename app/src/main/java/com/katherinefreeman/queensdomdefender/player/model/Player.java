package com.katherinefreeman.queensdomdefender.player.model;

import com.katherinefreeman.queensdomdefender.card.model.Card;

import java.util.ArrayList;

public class Player {

    private ArrayList<Card> deck;
    private ArrayList<Card> hand;
    private ArrayList<Card> field;
    private int health;
    private int energy;

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public ArrayList<Card> getField() {
        return field;
    }

    public void setField(ArrayList<Card> field) {
        this.field = field;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
