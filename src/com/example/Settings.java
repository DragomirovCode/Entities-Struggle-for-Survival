package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Settings {
    private static File fileURL = new File("src/config");
    public static String predatorAppearance;
    public static String herbivoreAppearance;
    public static String grassAppearance;
    public static String rockAppearance;
    public static String treeAppearance;
    public static String polypithAppearance;

    public int PredatorHP;
    public int HerbivoreHP;
    public int GrassHP;
    public int RockHP;
    public int TreeHP;
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
                    case "predatorAppearance":
                        predatorAppearance = value;
                        break;
                    case "herbivoreAppearance":
                        herbivoreAppearance = value;
                        break;
                    case "grassAppearance":
                        grassAppearance = value;
                        break;
                    case "rockAppearance":
                        rockAppearance = value;
                        break;
                    case "treeAppearance":
                        treeAppearance = value;
                        break;
                    case "polypithAppearance":
                        polypithAppearance = value;
                        break;
                    case "PredatorHP":
                        PredatorHP = Integer.parseInt(value);
                        break;
                    case "HerbivoreHP":
                        HerbivoreHP = Integer.parseInt(value);
                        break;
                    case "GrassHP":
                        GrassHP = Integer.parseInt(value);
                        break;
                    case "RockHP":
                        RockHP = Integer.parseInt(value);
                        break;
                    case "TreeHP":
                        TreeHP = Integer.parseInt(value);
                        break;
                    case "polypithHP":
                        polypithHP = Integer.parseInt(value);
                        break;
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
