package com.example.entities;

import com.example.Coordinates;

public class Predator extends Creature{
    public Predator(String Appearance, Coordinates coordinates) {
        super(Appearance, coordinates);
    }

    @Override
    protected void makeMove() {}
}
