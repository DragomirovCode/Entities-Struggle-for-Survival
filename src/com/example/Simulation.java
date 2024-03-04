package com.example;

import com.example.entities.Entity;

public class Simulation {
    public void renderMap(Map map){
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
}
