package com.example;

import java.util.Objects;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Coordinates shift(CoordinatesShift shift, Mapping map){
        int newX = this.x + shift.getX();
        int newY = this.y + shift.getY();
        if(canShift(shift, map)){
            return new Coordinates(newX, newY);
        }
        return this;
    }

    public boolean canShift(CoordinatesShift shift, Mapping map){
        int newX = this.x + shift.getX();
        int newY = this.y + shift.getY();
        return newX >= 0 && newX < map.getWidth() && newY >= 0 && newY < map.getHeight();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
