package com.example.entities;

import com.example.Coordinates;

abstract public class Creature  extends Entity {
    public Creature(Coordinates coordinates) {
        super(coordinates);
    }

    protected abstract void makeMove();
}
