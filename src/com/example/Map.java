package com.example;

import com.example.entities.Entity;

import java.util.HashMap;

public class Map {
    private HashMap<Coordinates, Entity> entities = new HashMap<>();

    public void addEntity(Coordinates coordinates, Entity entity){
        entities.put(coordinates, entity);
    }

    public void updateEntityPosition(Coordinates from, Coordinates to){
        Entity entity = getEntity(from);
        removeEntity(from);
        addEntity(to, entity);
    }

    public void removeEntity(Coordinates coordinates){
        entities.remove(coordinates);
    }

    public Entity getEntity(Coordinates coordinates){
        return entities.get(coordinates);
    }

    public HashMap<Coordinates, Entity> getEntities() {
        return entities;
    }
}
