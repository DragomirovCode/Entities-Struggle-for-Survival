package com.example.entities;

import com.example.Coordinates;
import com.example.CoordinatesShift;

import java.util.Set;

public class Predator extends Creature{
    public Predator(String Appearance, Coordinates coordinates) {
        super(Appearance, coordinates);
    }

    @Override
    protected void makeMove() {}

    @Override
    protected Set<CoordinatesShift> getPossibleMoves() {
        return null;
    }
}
