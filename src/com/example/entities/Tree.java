package com.example.entities;

import com.example.Coordinates;

public class Tree extends Entity {
    public Tree(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    protected String getAppearance() {
        return null;
    }
}
