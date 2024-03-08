package com.example;

import com.example.entities.Entity;

import java.util.Scanner;

public class Simulation {
    private static String mapSize = "";
    public void renderMap(Mapping map){
        for (int y = 0; y < map.getHeight(); y++){
            for (int x = 0; x < map.getWidth(); x++){
                boolean entityFound = false;
                for (Entity entity: map.getEntities().values()) {
                    if(entity.getCoordinates().getX() == x && entity.getCoordinates().getY() == y ){
                        System.out.print(entity.getAppearance().charAt(0) + " ");
                        entityFound = true;
                        break;
                    }
                }
                if(!entityFound){
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
    public void initActions(){
        greet();
        Mapping mapping = Settings.createMap(Integer.parseInt(mapSize));
        mapping.FillRandomPositions(mapSize);
        renderMap(mapping);
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
