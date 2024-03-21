package com.example;

public class Settings {
    public static String predatorAppearance = "🦊";
    public static String herbivoreAppearance = "🐰";
    public static String grassAppearance = "🌾";
    public static String rockAppearance = "🗻";
    public static String treeAppearance = "🌳";

    public int PredatorHP = 2;
    public int HerbivoreHP = 2;
    public int GrassHP = 2;
    public int RockHP = 0;
    public int TreeHP = 0;

    public static Mapping createMap(int size){
        return switch (size) {
            case 1 -> new Mapping(9, 9);
            case 2 -> new Mapping(10, 10);
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
