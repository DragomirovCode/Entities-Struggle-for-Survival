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

    public static int maxPredatorCount;
    public static int maxHerbivoreCount;
    public static int maxGrassCount;
    public static int maxRockCount;
    public static int maxTreeCount;
    public static int maxPolypithCount;

    public static int width;
    public static int height;

    public static Mapping createMap(){
        return (new Mapping(width, height));
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

                    case "predatorHP" -> predatorHP = Integer.parseInt(value);
                    case "herbivoreHP" -> herbivoreHP = Integer.parseInt(value);
                    case "grassHP" -> grassHP = Integer.parseInt(value);
                    case "rockHP" -> rockHP = Integer.parseInt(value);
                    case "treeHP" -> treeHP = Integer.parseInt(value);
                    case "polypithHP" -> polypithHP = Integer.parseInt(value);

                    case "maxPredatorCount" -> maxPredatorCount = Integer.parseInt(value);
                    case "maxHerbivoreCount" -> maxHerbivoreCount = Integer.parseInt(value);
                    case "maxGrassCount" -> maxGrassCount = Integer.parseInt(value);
                    case "maxRockCount" -> maxRockCount = Integer.parseInt(value);
                    case "maxTreeCount" -> maxTreeCount = Integer.parseInt(value);
                    case "maxPolypithCount" -> maxPolypithCount = Integer.parseInt(value);

                    case "width" -> width = Integer.parseInt(value);
                    case "height" -> height = Integer.parseInt(value);
                }
            }
        }
        scanner.close();
    }
}
