package com.example.entities;

import com.example.Coordinates;
import com.example.CoordinatesShift;
import com.example.Map;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Herbivore extends Creature{
    public Herbivore(String Appearance, Coordinates coordinates) {
        super(Appearance, coordinates);
    }

    @Override
    public void makeMove(Coordinates from, Coordinates to, List<Coordinates> path, Map map) {

    }

    @Override
    protected Set<CoordinatesShift> getPossibleMoves() {
        return new HashSet<>(Arrays.asList(
                new CoordinatesShift(1, 0),
                new CoordinatesShift(-1, 0),
                new CoordinatesShift(0, -1),
                new CoordinatesShift(0, 1)
        ));
    }

    @Override
    public List<Coordinates> searchPath(Coordinates start, Coordinates end, Map map) {

        return null;
    }
}
