package com.example.entities;

import com.example.Coordinates;
import com.example.Entity;

abstract public class Creature  extends Entity {
    public Creature(Coordinates coordinates) {
        super(coordinates);
    }

    protected abstract void makeMove();
}
