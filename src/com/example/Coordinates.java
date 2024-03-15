package com.example;

import java.util.Objects;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public static int calculateDistance(Coordinates c1, Coordinates c2) {
        int x1 = c1.getX();
        int y1 = c1.getY();
        int x2 = c2.getX();
        int y2 = c2.getY();

        // Используем формулу расстояния между двумя точками (x1, y1) и (x2, y2):
        // sqrt((x2 - x1)^2 + (y2 - y1)^2)

        return (int) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
    public boolean areAdjacent(Coordinates coord1, Coordinates coord2) {
        int xDiff = Math.abs(coord1.getX() - coord2.getX());
        int yDiff = Math.abs(coord1.getY() - coord2.getY());

        // Координаты считаются смежными, если разница по каждой оси не превышает 1
        return xDiff <= 1 && yDiff <= 1;
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
