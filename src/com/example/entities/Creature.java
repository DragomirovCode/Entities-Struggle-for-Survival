package com.example.entities;

import com.example.Coordinates;

abstract public class Creature  extends Entity {
    public Creature(String Appearance, Coordinates coordinates) {
        super(Appearance, coordinates);
    }

    protected abstract void makeMove();
}
