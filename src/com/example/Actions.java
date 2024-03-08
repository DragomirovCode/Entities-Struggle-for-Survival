package com.example;

import java.util.Scanner;

public class Actions {

    private Simulation simulation = new Simulation();
    private static String mapSize = "";
    public void initActions(){
        greet();
        Mapping mapping = Settings.createMap(Integer.parseInt(mapSize));
        mapping.FillRandomPositions(mapSize);
        simulation.renderMap(mapping);
    }

    private void greet(){
        System.out.println("Привет! Выбери размер карты");
        System.out.println("1 - средней, 2 - большой, 3 - стандартный");
        Scanner scanner = new Scanner(System.in);
        while(true){
            mapSize = scanner.nextLine();
            switch (mapSize) {
                case "1", "2", "3":
                    return;
                default:
                    System.out.println("Пожалуйста, выберите один из предложенных вариантов");
                    break;
            }
        }
    }
}
