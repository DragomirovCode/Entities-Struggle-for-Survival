package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Settings {
    private final File fileURL = new File("src/config");
    public static String predatorAppearance;
    public static String herbivoreAppearance;
    public static String grassAppearance;
    public static String rockAppearance;
    public static String treeAppearance;
    public static String polypithAppearance;

    public int predatorHP;
    public int herbivoreHP;
    public int grassHP;
    public int rockHP;
    public int treeHP;
    public int polypithHP;

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

    public void loadSettingsFromFile() throws FileNotFoundException  {
        Scanner scanner = new Scanner(fileURL);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] splitResult = line.split("=");

            if (splitResult.length == 2) {
                String key = splitResult[0].trim();
                String value = splitResult[1].trim();

                switch (key) {
                    case "predatorAppearance" -> predatorAppearance = value;
                    case "herbivoreAppearance" -> herbivoreAppearance = value;
                    case "grassAppearance" -> grassAppearance = value;
                    case "rockAppearance" -> rockAppearance = value;
                    case "treeAppearance" -> treeAppearance = value;
                    case "polypithAppearance" -> polypithAppearance = value;
                    case "PredatorHP" -> predatorHP = Integer.parseInt(value);
                    case "HerbivoreHP" -> herbivoreHP = Integer.parseInt(value);
                    case "GrassHP" -> grassHP = Integer.parseInt(value);
                    case "RockHP" -> rockHP = Integer.parseInt(value);
                    case "TreeHP" -> treeHP = Integer.parseInt(value);
                    case "polypithHP" -> polypithHP = Integer.parseInt(value);
                }
            }
        }
        scanner.close();
    }
}
/**
 * Хп,+
 * скины,+
 * Их количество
 */
