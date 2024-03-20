package com.example;

public class Settings {
    public static String predatorAppearance = "P";
    public static String herbivoreAppearance = "H";
    public static String grassAppearance = "G";
    public static String rockAppearance = "R";
    public static String treeAppearance = "T";
    public static String polypithAppearance = "?";

    public int PredatorHP = 2;
    public int HerbivoreHP = 2;
    public int GrassHP = 2;
    public int RockHP = 0;
    public int TreeHP = 0;
    public int polypithHP = 2;

    public static Mapping createMap(int size){
        return switch (size) {
            case 1 -> new Mapping(11, 11);
            case 2 -> new Mapping(13, 13);
            default -> new Mapping(8, 8);
        };
    }

    public static int calculateSpeed(int input){
        return switch (input) {
            case 1 -> 1000;
            case 2 -> 1500;
            default -> 2500;
        };
    }
}
