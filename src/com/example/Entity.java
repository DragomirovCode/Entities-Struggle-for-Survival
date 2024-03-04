package com.example;

abstract public class Entity {
    private Coordinates coordinates;

    public Entity(Coordinates coordinates){
        this.coordinates = coordinates;
    }

    protected abstract String getAppearance();
}
