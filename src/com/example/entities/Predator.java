package com.example.entities;

import com.example.Coordinates;

public class Predator extends Creature{
    public Predator(Coordinates coordinates) {
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
