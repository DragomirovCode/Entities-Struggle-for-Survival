package com.example.entities;

import com.example.Coordinates;

public class Herbivore extends Creature{
    public Herbivore(String Appearance, Coordinates coordinates) {
        super(Appearance, coordinates);
    }

    @Override
    protected void makeMove() {}
}
