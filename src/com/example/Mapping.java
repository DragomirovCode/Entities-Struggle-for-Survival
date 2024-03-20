package com.example;

import com.example.entities.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static com.example.Settings.*;

public class Mapping {
    private Settings settings = new Settings();
    private Random random = new Random();
    private int width;
    private int height;
    private HashMap<Coordinates, Entity> entities;
    private static Coordinates randomCoordinates;
    private int creatureCountPredator = 0;
    private int creatureCountHerbivore = 0;
    private int creatureCountGrass = 0;
    private int creatureCountRock = 0;
    private int creatureCountTree = 0;
    private int creatureCountPolypith = 0;
    public Mapping(int width, int height){
        this.width = width;
        this.height = height;
        this.entities = new HashMap<>();
    }

    public void fillRandomPositions(String mapSize){
        if(mapSize.equals("1")){
            while ((creatureCountPredator < 3) || (creatureCountHerbivore < 3) || (creatureCountGrass < 3) ||
                    (creatureCountRock < 3) || (creatureCountTree < 3) || (creatureCountPolypith < 2)){

                int randomCoordinatesForX = random.nextInt(11);
                int randomCoordinatesForY = random.nextInt(11);

                randomCoordinates = new Coordinates(randomCoordinatesForX, randomCoordinatesForY);

                if(getAvailabilityStatusOfCoordinate(randomCoordinates) && isFarEnough(randomCoordinates)){
                    if(creatureCountPredator < 3) {
                        Predator newPredator = new Predator(predatorAppearance, randomCoordinates, settings.PredatorHP);
                        addEntity(newPredator.getCoordinates(), newPredator);
                        creatureCountPredator++;
                    }else if(creatureCountHerbivore < 3){
                        Herbivore newHerbivore = new Herbivore(herbivoreAppearance, randomCoordinates, settings.HerbivoreHP);
                        addEntity(newHerbivore.getCoordinates(), newHerbivore);
                        creatureCountHerbivore++;
                    }else if(creatureCountGrass < 3){
                        Grass newGrass = new Grass(grassAppearance, randomCoordinates, settings.GrassHP);
                        addEntity(newGrass.getCoordinates(), newGrass);
                        creatureCountGrass++;
                    }else if(creatureCountRock < 3){
                        Rock newRock = new Rock(rockAppearance, randomCoordinates, settings.RockHP);
                        addEntity(newRock.getCoordinates(), newRock);
                        creatureCountRock++;
                    }else if(creatureCountPolypith < 1){
                        Polypith newPolypith = new Polypith(polypithAppearance, randomCoordinates, settings.polypithHP);
                        addEntity(newPolypith.getCoordinates(), newPolypith);
                        creatureCountPolypith++;
                    }else{
                        Tree newTree = new Tree(treeAppearance, randomCoordinates, settings.TreeHP);
                        addEntity(newTree.getCoordinates(), newTree);
                        creatureCountTree++;
                    }
                }
            }
        }else if(mapSize.equals("2")){
            while ((creatureCountPredator < 4) || (creatureCountHerbivore < 4) || (creatureCountGrass < 4) ||
                    (creatureCountRock < 4) || (creatureCountTree < 4) || (creatureCountPolypith < 2)){

                int randomCoordinatesForX = random.nextInt(13);
                int randomCoordinatesForY = random.nextInt(13);

                randomCoordinates = new Coordinates(randomCoordinatesForX, randomCoordinatesForY);

                if(getAvailabilityStatusOfCoordinate(randomCoordinates) && isFarEnough(randomCoordinates)){
                    if(creatureCountPredator < 4) {
                        Predator newPredator = new Predator(predatorAppearance, randomCoordinates, settings.PredatorHP);
                        addEntity(newPredator.getCoordinates(), newPredator);
                        creatureCountPredator++;
                    }else if(creatureCountHerbivore < 4){
                        Herbivore newHerbivore = new Herbivore(herbivoreAppearance, randomCoordinates, settings.HerbivoreHP);
                        addEntity(newHerbivore.getCoordinates(), newHerbivore);
                        creatureCountHerbivore++;
                    }else if(creatureCountGrass < 4){
                        Grass newGrass = new Grass(grassAppearance, randomCoordinates, settings.GrassHP);
                        addEntity(newGrass.getCoordinates(), newGrass);
                        creatureCountGrass++;
                    }else if(creatureCountRock < 4){
                        Rock newRock = new Rock(rockAppearance, randomCoordinates, settings.RockHP);
                        addEntity(newRock.getCoordinates(), newRock);
                        creatureCountRock++;
                    }else if(creatureCountPolypith < 2){
                        Polypith newPolypith = new Polypith(polypithAppearance, randomCoordinates, settings.polypithHP);
                        addEntity(newPolypith.getCoordinates(), newPolypith);
                        creatureCountPolypith++;
                    }else{
                        Tree newTree = new Tree(treeAppearance, randomCoordinates, settings.TreeHP);
                        addEntity(newTree.getCoordinates(), newTree);
                        creatureCountTree++;
                    }
                }
            }
        }else if(mapSize.equals("3")){
            while ((creatureCountPredator < 2) || (creatureCountHerbivore < 2) || (creatureCountGrass < 2) ||
                    (creatureCountRock < 2) || (creatureCountTree < 2) || (creatureCountPolypith < 1)){

                int randomCoordinatesForX = random.nextInt(8);
                int randomCoordinatesForY = random.nextInt(8);

                randomCoordinates = new Coordinates(randomCoordinatesForX, randomCoordinatesForY);

                if(getAvailabilityStatusOfCoordinate(randomCoordinates) && isFarEnough(randomCoordinates)){
                    if(creatureCountPredator < 2) {
                        Predator newPredator = new Predator(predatorAppearance, randomCoordinates, settings.PredatorHP);
                        addEntity(newPredator.getCoordinates(), newPredator);
                        creatureCountPredator++;
                    }else if(creatureCountHerbivore < 2){
                        Herbivore newHerbivore = new Herbivore(herbivoreAppearance, randomCoordinates, settings.HerbivoreHP);
                        addEntity(newHerbivore.getCoordinates(), newHerbivore);
                        creatureCountHerbivore++;
                    }else if(creatureCountGrass < 2){
                        Grass newGrass = new Grass(grassAppearance, randomCoordinates, settings.GrassHP);
                        addEntity(newGrass.getCoordinates(), newGrass);
                        creatureCountGrass++;
                    }else if(creatureCountRock < 2){
                        Rock newRock = new Rock(rockAppearance, randomCoordinates, settings.RockHP);
                        addEntity(newRock.getCoordinates(), newRock);
                        creatureCountRock++;
                    }else if(creatureCountPolypith < 1){
                        Polypith newPolypith = new Polypith(polypithAppearance, randomCoordinates, settings.polypithHP);
                        addEntity(newPolypith.getCoordinates(), newPolypith);
                        creatureCountPolypith++;
                    }else{
                        Tree newTree = new Tree(treeAppearance, randomCoordinates, settings.TreeHP);
                        addEntity(newTree.getCoordinates(), newTree);
                        creatureCountTree++;
                    }
                }
            }
        }
    }


    private boolean isFarEnough(Coordinates newCoordinates) {
        for (Entity entity : entities.values()) {
            int minDistance = 2;
            if (entity instanceof Predator) {
                int distance = (int) Math.sqrt(Math.pow(entity.getCoordinates().getX() - newCoordinates.getX(), 2) +
                        Math.pow(entity.getCoordinates().getY() - newCoordinates.getY(), 2));
                if (distance < minDistance) {
                    return false;
                }
            }
            if(entity instanceof Herbivore){
                int distance = (int) Math.sqrt(Math.pow(entity.getCoordinates().getX() - newCoordinates.getX(), 2) +
                        Math.pow(entity.getCoordinates().getY() - newCoordinates.getY(), 2));
                if (distance < minDistance) {
                    return false;
                }
            }
            if(entity instanceof  Grass){
                int distance = (int) Math.sqrt(Math.pow(entity.getCoordinates().getX() - newCoordinates.getX(), 2) +
                        Math.pow(entity.getCoordinates().getY() - newCoordinates.getY(), 2));
                if (distance < minDistance) {
                    return false;
                }
            }
            if(entity instanceof Rock){
                int distance = (int) Math.sqrt(Math.pow(entity.getCoordinates().getX() - newCoordinates.getX(), 2) +
                        Math.pow(entity.getCoordinates().getY() - newCoordinates.getY(), 2));
                if (distance < minDistance) {
                    return false;
                }
            }
            if(entity instanceof Tree){
                int distance = (int) Math.sqrt(Math.pow(entity.getCoordinates().getX() - newCoordinates.getX(), 2) +
                        Math.pow(entity.getCoordinates().getY() - newCoordinates.getY(), 2));
                if (distance < minDistance) {
                    return false;
                }
            }
            if(entity instanceof Polypith){
                int distance = (int) Math.sqrt(Math.pow(entity.getCoordinates().getX() - newCoordinates.getX(), 2) +
                        Math.pow(entity.getCoordinates().getY() - newCoordinates.getY(), 2));
                if (distance < minDistance) {
                    return false;
                }
            }
        }
        return true;
    }

    public static List<Herbivore> findAllHerbivore(Mapping mapping) {
        List<Herbivore> allHerbivore = new ArrayList<>();

        for (Entity entity : mapping.getEntities().values()) {
            if (entity instanceof Herbivore) {
                allHerbivore.add((Herbivore) entity);
            }
        }

        return allHerbivore;
    }

    public static List<Predator> findAllPredators(Mapping mapping) {
        List<Predator> allPredators = new ArrayList<>();

        for (Entity entity : mapping.getEntities().values()) {
            if (entity instanceof Predator) {
                allPredators.add((Predator) entity);
            }
        }

        return allPredators;
    }

    public static List<Entity> findEcosystemEntities(Mapping mapping){
        List<Entity> ecosystemEntities = new ArrayList<>();

        for (Entity entity: mapping.getEntities().values()){
            if (entity instanceof Predator){
                ecosystemEntities.add(entity);
            }
            if (entity instanceof Herbivore){
                ecosystemEntities.add(entity);
            }
            if (entity instanceof Grass){
                ecosystemEntities.add(entity);
            }
            if (entity instanceof  Polypith){
                ecosystemEntities.add(entity);
            }
        }
        return ecosystemEntities;
    }

    public static List<Grass> findAllGrasses(Mapping mapping){
        List<Grass> allGrasses = new ArrayList<>();

        for (Entity entity: mapping.getEntities().values()){
            if(entity instanceof Grass){
                allGrasses.add((Grass) entity);
            }
        }
        return allGrasses;
    }

    public boolean checkSpeciesCollision(Coordinates coordinates, Entity entity){
        if (entities.get(coordinates) instanceof Predator && entity instanceof Predator) {
            return false;
        }
        if (entities.get(coordinates) instanceof Herbivore && entity instanceof Herbivore) {
            return false;
        }
        return true;
    }

    public boolean determineTargetAtNextCoordinates(Coordinates next, Entity entity){
        if (entities.get(next) instanceof Herbivore && entity instanceof Predator){
            return true;
        }
        if (entities.get(next) instanceof Grass && entity instanceof Herbivore){
            return true;
        }
        return false;
    }

    public boolean getAvailabilityStatusOfCoordinate(Coordinates coordinates){
        return !entities.containsKey(coordinates);
    }

    public void setEntity(Coordinates coordinates, Entity entity){
        entities.put(coordinates, entity);
    }

    public void updateEntityPosition(Coordinates from, Coordinates to){
        Entity entity = getEntity(from);
        removeEntity(from);
        setEntity(to, entity);
    }

    public void addEntity(Coordinates coordinates, Entity entity){
        getEntities().put(coordinates, entity);
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
