package com.example.entities;

import com.example.Coordinates;
import com.example.Entity;

public class Tree extends Entity {
    public Tree(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    protected String getAppearance() {
        return null;
    }
}
