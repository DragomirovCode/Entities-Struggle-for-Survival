package com.example.entities;

import com.example.Coordinates;

abstract public class Entity {
    private Coordinates coordinates;

    private String Appearance;

    public Entity(String Appearance, Coordinates coordinates){
        this.coordinates = coordinates;
        this.Appearance = Appearance;
    }

    public String getAppearance() {
        return Appearance;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

}
