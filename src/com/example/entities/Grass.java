package com.example.entities;

import com.example.Coordinates;

public class Grass extends Entity {

    public Grass(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    protected String getAppearance() {
        return null;
    }
}
