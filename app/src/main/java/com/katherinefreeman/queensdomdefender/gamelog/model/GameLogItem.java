package com.katherinefreeman.queensdomdefender.gamelog.model;

public class GameLogItem {

    private String text;
    private int textColour;

    public GameLogItem(String text, int textColour) {
        this.text = text;
        this.textColour = textColour;
    }

    public String getText() {
        return text;
    }

    public int getTextColour() {
        return textColour;
    }

}
