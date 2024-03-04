package com.example;

public class Settings {

    public static Map createMap(int size){
        switch (size){
            case 1:
                return new Map(8, 8 );
            case 2:
                return new Map(10, 10);
            default:
                return new Map(5, 5);
        }
    }
}
