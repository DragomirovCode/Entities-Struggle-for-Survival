package com.example;

public class Settings {
    public static String predatorAppearance = "P";
    public static String herbivoreAppearance = "H";
    public static String grassAppearance = "G";
    public static String rockAppearance = "R";
    public static String treeAppearance = "T";

    public static Mapping createMap(int size){
        switch (size){
            case 1:
                return new Mapping(9, 9 );
            case 2:
                return new Mapping(10, 10);
            default:
                return new Mapping(8, 8);
        }
    }

    public static int calculateSpeed(int input){
        int speed = 0;
        switch (input){
            case 1:
                speed = 1000;
            case 2:
                speed = 1500;
            default:
                speed = 2500;
        }
        return speed;
    }
}
