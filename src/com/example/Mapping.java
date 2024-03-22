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

    public void fillRandomPositions(int maxPredatorCount, int maxHerbivoreCount, int maxGrassCount,
                                    int maxRockCount, int maxPolypithCount, int maxTreeCount) {

        while ((creatureCountPredator < maxPredatorCount)
                || (creatureCountHerbivore < maxHerbivoreCount)
                || (creatureCountGrass < maxGrassCount)
                || (creatureCountRock < maxRockCount)
                || (creatureCountTree < maxTreeCount)
                || (creatureCountPolypith < maxPolypithCount)) {

            // Проверяем, если количество существ уже превышает максимальное количество
            if(entities.size() >= width * height) {
                System.out.println("Слишком много сущностей на карте. Невозможно добавить больше.");
                break; // Выходим из цикла, так как больше не можем добавить сущностей
            }

            int randomCoordinatesForX = random.nextInt(Settings.width);
            int randomCoordinatesForY = random.nextInt(Settings.height);

            randomCoordinates = new Coordinates(randomCoordinatesForX, randomCoordinatesForY);

            if (getAvailabilityStatusOfCoordinate(randomCoordinates) && isFarEnough(randomCoordinates)) {
                if (creatureCountPredator < maxPredatorCount) {
                    Predator newPredator = new Predator(predatorAppearance, randomCoordinates, settings.predatorHP);
                    addEntity(newPredator.getCoordinates(), newPredator);
                    creatureCountPredator++;
                } else if (creatureCountHerbivore < maxHerbivoreCount) {
                    Herbivore newHerbivore = new Herbivore(herbivoreAppearance, randomCoordinates, settings.herbivoreHP);
                    addEntity(newHerbivore.getCoordinates(), newHerbivore);
                    creatureCountHerbivore++;
                } else if (creatureCountGrass < maxGrassCount) {
                    Grass newGrass = new Grass(grassAppearance, randomCoordinates, settings.grassHP);
                    addEntity(newGrass.getCoordinates(), newGrass);
                    creatureCountGrass++;
                } else if (creatureCountRock < maxRockCount) {
                    Rock newRock = new Rock(rockAppearance, randomCoordinates, settings.rockHP);
                    addEntity(newRock.getCoordinates(), newRock);
                    creatureCountRock++;
                } else if (creatureCountPolypith < maxPolypithCount) {
                    Polypith newPolypith = new Polypith(polypithAppearance, randomCoordinates, settings.polypithHP);
                    addEntity(newPolypith.getCoordinates(), newPolypith);
                    creatureCountPolypith++;
                } else {
                    Tree newTree = new Tree(treeAppearance, randomCoordinates, settings.treeHP);
                    addEntity(newTree.getCoordinates(), newTree);
                    creatureCountTree++;
                }
            }
        }
    }

    private boolean isFarEnough(Coordinates newCoordinates) {
        int minDistance = 2;
        for (Entity entity : entities.values()) {
            int distance = (int) Math.sqrt(Math.pow(entity.getCoordinates().getX() - newCoordinates.getX(), 2) +
                    Math.pow(entity.getCoordinates().getY() - newCoordinates.getY(), 2));
            if (distance < minDistance) {
                return false;
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

    public static List<Polypith> findAllPolypith(Mapping mapping){
        List<Polypith> allPolypith = new ArrayList<>();

        for(Entity entity: mapping.getEntities().values()){
            if(entity instanceof Polypith){
                allPolypith.add((Polypith) entity);
            }
        }
        return allPolypith;
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
        if((entities.get(next) instanceof Herbivore || entities.get(next) instanceof Grass
                || entities.get(next) instanceof Predator || entities.get(next) instanceof Polypith) &&
                entity instanceof Polypith){
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
