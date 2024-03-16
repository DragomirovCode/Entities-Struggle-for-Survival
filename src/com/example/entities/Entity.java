package com.example.entities;

import com.example.Coordinates;

abstract public class Entity {
    private Coordinates coordinates;

    private String Appearance;

    private int HP;

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

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "coordinates=" + coordinates +
                ", Appearance='" + Appearance + '\'' +
                '}';
    }
}
