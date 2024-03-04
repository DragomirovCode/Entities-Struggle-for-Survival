package com.example.entities;

import com.example.Coordinates;

public class Herbivore extends Creature{
    public Herbivore(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    protected void makeMove() {

    }

    @Override
    protected String getAppearance() {
        return null;
    }
}
