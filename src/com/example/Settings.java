package com.example;

public class Settings {
    public static String predatorAppearance = "P";
    public static String herbivoreAppearance = "H";
    public static String grassAppearance = "G";
    public static String rockAppearance = "R";
    public static String treeAppearance = "T";

    public int PredatorHP = 2;
    public int HerbivoreHP = 2;
    public int GrassHP = 2;
    public int RockHP = 0;
    public int TreeHP = 0;

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
                break;
            case 2:
                speed = 1500;
                break;
            default:
                speed = 2500;
                break;
        }
        return speed;
    }
}
