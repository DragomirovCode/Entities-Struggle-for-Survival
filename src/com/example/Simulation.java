package com.example;

import com.example.entities.Entity;
import com.example.entities.Herbivore;
import com.example.entities.Predator;

import java.util.List;

public class Simulation {
    private Actions actions;

    public Simulation(Actions actions) {
        this.actions = actions;
    }

    public void renderMap(Mapping map){
        for (int y = 0; y < map.getHeight(); y++){
            for (int x = 0; x < map.getWidth(); x++){
                boolean entityFound = false;
                for (Entity entity: map.getEntities().values()) {
                    if (entity != null && entity.getCoordinates().getX() == x && entity.getCoordinates().getY() == y) {
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
