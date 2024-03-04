package com.example.entities;

import com.example.Coordinates;

public class Rock extends Entity {
    public Rock(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    protected String getAppearance() {
        return null;
    }
}
