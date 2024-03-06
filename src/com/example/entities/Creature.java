package com.example.entities;

import com.example.Coordinates;
import com.example.CoordinatesShift;
import com.example.Map;

import java.util.Set;

abstract public class Creature extends Entity {
    public Creature(String Appearance, Coordinates coordinates) {
        super(Appearance, coordinates);
    }

    protected abstract void makeMove();

    protected abstract Set<CoordinatesShift> getPossibleMoves();

    protected abstract Coordinates  searchPath(Coordinates start, Coordinates end, Map map);
}
