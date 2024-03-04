package com.example.entities;

import com.example.Coordinates;
import com.example.Entity;

public class Rock extends Entity {
    public Rock(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    protected String getAppearance() {
        return null;
    }
}
