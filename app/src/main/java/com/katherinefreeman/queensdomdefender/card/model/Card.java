package com.katherinefreeman.queensdomdefender.card.model;

public class Card {

    private String name;
    private int imageResource;
    private int health;
    private int energy;
    private int attack;
    private CardType type;

    public Card(String name, int imageResource, int health, int energy, int attack, CardType type) {
        this.name = name;
        this.imageResource = imageResource;
        this.health = health;
        this.energy = energy;
        this.attack = attack;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public int getHealth() {
        return health;
    }

    public int getEnergy() {
        return energy;
    }

    public int getAttack() {
        return attack;
    }

    public CardType getType() {
        return type;
    }
}
