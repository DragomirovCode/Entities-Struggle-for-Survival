package com.example;

import com.example.entities.*;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import static com.example.Settings.*;

public class Mapping {
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


    public Mapping(int width, int height){
        this.width = width;
        this.height = height;
        this.entities = new HashMap<>();
    }

    public void FillRandomPositions(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введи размер карты");
        System.out.println("1-средняя, 2 - большая, 3 - стандартная");
        String mapSize = scanner.nextLine();
        if(mapSize.equals("1")){
            while ((creatureCountPredator < 3) || (creatureCountHerbivore < 3) || (creatureCountGrass < 3) ||
                    (creatureCountRock < 3) || (creatureCountTree < 3)){

                int randomCoordinatesForX = random.nextInt(8);
                int randomCoordinatesForY = random.nextInt(8);

                randomCoordinates = new Coordinates(randomCoordinatesForX, randomCoordinatesForY);

                if(getAvailabilityStatusOfCoordinate(randomCoordinates)){
                    if(creatureCountPredator < 3) {
                        Predator newPredator = new Predator(predatorAppearance, randomCoordinates);
                        addEntity(newPredator.getCoordinates(), newPredator);
                        creatureCountPredator++;
                    }else if(creatureCountHerbivore < 3){
                        Herbivore newHerbivore = new Herbivore(herbivoreAppearance, randomCoordinates);
                        addEntity(newHerbivore.getCoordinates(), newHerbivore);
                        creatureCountHerbivore++;
                    }else if(creatureCountGrass < 3){
                        Grass newGrass = new Grass(grassAppearance, randomCoordinates);
                        addEntity(newGrass.getCoordinates(), newGrass);
                        creatureCountGrass++;
                    }else if(creatureCountRock < 3){
                        Rock newRock = new Rock(rockAppearance, randomCoordinates);
                        addEntity(newRock.getCoordinates(), newRock);
                        creatureCountRock++;
                    }else{
                        Tree newTree = new Tree(treeAppearance, randomCoordinates);
                        addEntity(newTree.getCoordinates(), newTree);
                        creatureCountTree++;
                    }
                }
            }
        }else if(mapSize.equals("2")){
            while ((creatureCountPredator < 4) || (creatureCountHerbivore < 4) || (creatureCountGrass < 4) ||
                    (creatureCountRock < 4) || (creatureCountTree < 4)){

                int randomCoordinatesForX = random.nextInt(10);
                int randomCoordinatesForY = random.nextInt(10);

                randomCoordinates = new Coordinates(randomCoordinatesForX, randomCoordinatesForY);

                if(getAvailabilityStatusOfCoordinate(randomCoordinates)){
                    if(creatureCountPredator < 4) {
                        Predator newPredator = new Predator(predatorAppearance, randomCoordinates);
                        addEntity(newPredator.getCoordinates(), newPredator);
                        creatureCountPredator++;
                    }else if(creatureCountHerbivore < 4){
                        Herbivore newHerbivore = new Herbivore(herbivoreAppearance, randomCoordinates);
                        addEntity(newHerbivore.getCoordinates(), newHerbivore);
                        creatureCountHerbivore++;
                    }else if(creatureCountGrass < 4){
                        Grass newGrass = new Grass(grassAppearance, randomCoordinates);
                        addEntity(newGrass.getCoordinates(), newGrass);
                        creatureCountGrass++;
                    }else if(creatureCountRock < 4){
                        Rock newRock = new Rock(rockAppearance, randomCoordinates);
                        addEntity(newRock.getCoordinates(), newRock);
                        creatureCountRock++;
                    }else{
                        Tree newTree = new Tree(treeAppearance, randomCoordinates);
                        addEntity(newTree.getCoordinates(), newTree);
                        creatureCountTree++;
                    }
                }
            }
        }else if(mapSize.equals("3")){
            while ((creatureCountPredator < 2) || (creatureCountHerbivore < 2) || (creatureCountGrass < 2) ||
                    (creatureCountRock < 2) || (creatureCountTree < 2)){

                int randomCoordinatesForX = random.nextInt(5);
                int randomCoordinatesForY = random.nextInt(5);

                randomCoordinates = new Coordinates(randomCoordinatesForX, randomCoordinatesForY);

                if(getAvailabilityStatusOfCoordinate(randomCoordinates)){
                    if(creatureCountPredator < 2) {
                        Predator newPredator = new Predator(predatorAppearance, randomCoordinates);
                        addEntity(newPredator.getCoordinates(), newPredator);
                        creatureCountPredator++;
                    }else if(creatureCountHerbivore < 2){
                        Herbivore newHerbivore = new Herbivore(herbivoreAppearance, randomCoordinates);
                        addEntity(newHerbivore.getCoordinates(), newHerbivore);
                        creatureCountHerbivore++;
                    }else if(creatureCountGrass < 2){
                        Grass newGrass = new Grass(grassAppearance, randomCoordinates);
                        addEntity(newGrass.getCoordinates(), newGrass);
                        creatureCountGrass++;
                    }else if(creatureCountRock < 2){
                        Rock newRock = new Rock(rockAppearance, randomCoordinates);
                        addEntity(newRock.getCoordinates(), newRock);
                        creatureCountRock++;
                    }else{
                        Tree newTree = new Tree(treeAppearance, randomCoordinates);
                        addEntity(newTree.getCoordinates(), newTree);
                        creatureCountTree++;
                    }
                }
            }
        }
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
