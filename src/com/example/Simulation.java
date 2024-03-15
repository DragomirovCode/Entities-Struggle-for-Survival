package com.example;

import com.example.entities.Entity;
import com.example.entities.Herbivore;
import com.example.entities.Predator;

import java.util.*;

public class Simulation {
    private Actions actions;

    private boolean moveInProgress = false;
    private Scanner scanner = new Scanner(System.in);

    public Simulation(Actions actions) {
        this.actions = actions;
    }

    public void renderMap(Mapping map) {
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                boolean entityFound = false;
                for (Entity entity : map.getEntities().values()) {
                    if (entity != null && entity.getCoordinates().getX() == x && entity.getCoordinates().getY() == y) {
                        System.out.print(entity.getAppearance().charAt(0) + " ");
                        entityFound = true;
                        break;
                    }
                }
                if (!entityFound) {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }


    public void startSimulation() {
        Mapping mapping = actions.getCurrentMap();

        while(true) {

            List<Predator> allPredators = Mapping.findAllPredators(mapping);
            List<Herbivore> allHerbivore = Mapping.findAllHerbivore(mapping);

            for (Predator predator : allPredators) {
                Herbivore closestHerbivore = findClosestHerbivore(predator, allHerbivore);
                if (closestHerbivore != null) {
                    List<Coordinates> path = predator.searchPath(predator.getCoordinates(),
                            closestHerbivore.getCoordinates(), mapping, predator);
                    Coordinates oldCoordinates = predator.getCoordinates();
                    predator.makeMove(predator.getCoordinates(), closestHerbivore.getCoordinates(),
                            path, mapping);
                    Coordinates newCoordinates = predator.getCoordinates();
                    if(!oldCoordinates.equals(newCoordinates)) {
                        renderMap(mapping);
                        System.out.println("===");
                    }
                }

            }
            if(allHerbivore.isEmpty()){
                break;
            }
        }
    }


    private Herbivore findClosestHerbivore(Predator predator, List<Herbivore> herbivores) {
        int minDistance = Integer.MAX_VALUE;
        Herbivore closestHerbivore = null;
        for (Herbivore herbivore : herbivores) {
            int distance = Coordinates.calculateDistance(predator.getCoordinates(), herbivore.getCoordinates());
            if (distance < minDistance) {
                minDistance = distance;
                closestHerbivore = herbivore;
            }
        }
        return closestHerbivore;
    }



    private void askForSingleMove() {
        System.out.println("Хотите сделать один ход?");
        System.out.println("1 - да, 2 - нет");
        while (true) {
            String actionChoice = scanner.nextLine();
            if (actionChoice.equals("1")) {
                moveInProgress = true;
                break;
            }else if(actionChoice.equals("2")){
                moveInProgress = false;
                break;
            }else {
                System.out.println("Пожалуйста, выберите один из предложенных вариантов");
            }
        }
    }
}
