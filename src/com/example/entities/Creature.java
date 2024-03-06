package com.example.entities;

import com.example.Coordinates;
import com.example.CoordinatesShift;
import com.example.Map;

import java.util.List;
import java.util.Set;

abstract public class Creature extends Entity {
    public Creature(String Appearance, Coordinates coordinates) {
        super(Appearance, coordinates);
    }

    public abstract void makeMove(Coordinates from, Coordinates to, List<Coordinates> path, Map map);

    protected abstract Set<CoordinatesShift> getPossibleMoves();

    public abstract List<Coordinates> searchPath(Coordinates start, Coordinates end, Map map);
}
