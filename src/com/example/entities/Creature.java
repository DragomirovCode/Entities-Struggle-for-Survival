package com.example.entities;

import com.example.Coordinates;
import com.example.CoordinatesShift;

import java.util.Set;

abstract public class Creature extends Entity {
    public Creature(String Appearance, Coordinates coordinates) {
        super(Appearance, coordinates);
    }

    protected abstract void makeMove();

    protected abstract Set<CoordinatesShift> getPossibleMoves();
}
