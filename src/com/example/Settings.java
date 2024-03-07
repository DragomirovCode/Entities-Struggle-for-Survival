package com.example;

public class Settings {

    public static Mapping createMap(int size){
        switch (size){
            case 1:
                return new Mapping(8, 8 );
            case 2:
                return new Mapping(10, 10);
            default:
                return new Mapping(5, 5);
        }
    }
}
