package com.example;

import com.example.entities.*;

import java.util.*;

public class Simulation {
    private PauseSimulation pauseSimulation = new PauseSimulation();
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

    public void startSimulation() throws InterruptedException {
        Mapping mapping = actions.getCurrentMap();
        boolean predatorMove = true;
        pauseSimulation.start();
        System.out.println("Введите 0, чтобы завершить симуляцию");
        while(true) {
                List<Predator> allPredators = Mapping.findAllPredators(mapping);
                List<Herbivore> allHerbivore = Mapping.findAllHerbivore(mapping);
                List<Grass> allGrasses = Mapping.findAllGrasses(mapping);

                List<Polypith> allPolypith = Mapping.findAllPolypith(mapping);
                List<Entity> ecosystemEntities = Mapping.findEcosystemEntities(mapping);
            if (predatorMove) {
                for (Predator predator : allPredators) {
                    Herbivore closestHerbivore = findClosestHerbivore(predator, allHerbivore);
                    if (closestHerbivore != null) {
                        List<Coordinates> pathPredator = predator.searchPath(
                                predator.getCoordinates(), closestHerbivore.getCoordinates(), mapping
                        );
                        Coordinates oldCoordinatesPredator = predator.getCoordinates();
                        if(!PauseSimulation.running){
                            return;
                        }
                        predator.makeMove(
                                predator.getCoordinates(), closestHerbivore.getCoordinates(), pathPredator, mapping, predator
                        );
                        Coordinates newCoordinatesPredator = predator.getCoordinates();
                        if (!oldCoordinatesPredator.equals(newCoordinatesPredator)) {
                            if(!PauseSimulation.running){
                                return;
                            }
                            renderMap(mapping);
                            System.out.println("===");
                            Thread.sleep(Actions.speed);
                            break;
                        }
                    }

                }
            }else {
                for (Herbivore herbivore : allHerbivore) {
                    Grass closestGrass = findClosestGrass(herbivore, allGrasses);
                    if (closestGrass != null) {
                        List<Coordinates> pathHerbivore = herbivore.searchPath(
                                herbivore.getCoordinates(), closestGrass.getCoordinates(), mapping
                        );
                        Coordinates oldCoordinatesHerbivore = herbivore.getCoordinates();
                        if(!PauseSimulation.running){
                            return;
                        }
                        herbivore.makeMove(
                                herbivore.getCoordinates(), closestGrass.getCoordinates(),
                                pathHerbivore, mapping, herbivore
                        );
                        Coordinates newCoordinatesHerbivore = herbivore.getCoordinates();
                        if (!oldCoordinatesHerbivore.equals(newCoordinatesHerbivore)) {
                            if(!PauseSimulation.running){
                                return;
                            }
                            renderMap(mapping);
                            System.out.println("===");
                            Thread.sleep(Actions.speed);
                            break;
                        }
                    }
                }
            }
            predatorMove = !predatorMove;
            if (allHerbivore.isEmpty()) {
                break;
            }

        }
    }
    
    public void nextTurn() {
        Mapping mapping = actions.getCurrentMap();
        boolean predatorMove = true;
        while (true) {
            List<Predator> allPredators = Mapping.findAllPredators(mapping);
            List<Herbivore> allHerbivore = Mapping.findAllHerbivore(mapping);
            List<Grass> allGrasses = Mapping.findAllGrasses(mapping);
            if (predatorMove) {
                for (Predator predator : allPredators) {
                    Herbivore closestHerbivore = findClosestHerbivore(predator, allHerbivore);
                    if (closestHerbivore != null) {
                        List<Coordinates> pathPredator = predator.searchPath(
                                predator.getCoordinates(), closestHerbivore.getCoordinates(), mapping
                        );
                        Coordinates oldCoordinatesPredator = predator.getCoordinates();
                        predator.makeMove(
                                predator.getCoordinates(), closestHerbivore.getCoordinates(), pathPredator,
                                mapping, predator
                        );
                        Coordinates newCoordinatesPredator = predator.getCoordinates();
                        if (!oldCoordinatesPredator.equals(newCoordinatesPredator)) {
                            askForSingleMove();
                            System.out.println("===Визуализация прошлых координат===");
                            renderMap(mapping);
                            System.out.println();
                            System.out.println("Координаты следующего хода: ");
                            System.out.println();
                            if (!moveInProgress) {
                                return;
                            }
                            break;
                        }
                    }
                }
            } else {
                for (Herbivore herbivore : allHerbivore) {
                    Grass closestGrass = findClosestGrass(herbivore, allGrasses);
                    if (closestGrass != null) {
                        List<Coordinates> pathHerbivore = herbivore.searchPath(
                                herbivore.getCoordinates(), closestGrass.getCoordinates(), mapping
                        );
                        Coordinates oldCoordinatesHerbivore = herbivore.getCoordinates();
                        herbivore.makeMove(
                                herbivore.getCoordinates(), closestGrass.getCoordinates(),
                                pathHerbivore, mapping, herbivore
                        );
                        Coordinates newCoordinatesHerbivore = herbivore.getCoordinates();
                        if (!oldCoordinatesHerbivore.equals(newCoordinatesHerbivore)) {
                            askForSingleMove();
                            System.out.println("===Визуализация прошлого хода===");
                            renderMap(mapping);
                            System.out.println();
                            System.out.println("Координаты следующего хода: ");
                            System.out.println();
                            if (!moveInProgress) {
                                return;
                            }
                            break;
                        }
                    }
                }
            }
            predatorMove = !predatorMove;
            if (allHerbivore.isEmpty()) {
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

    private Grass findClosestGrass(Herbivore herbivore, List<Grass> grasses) {
        int minDistance = Integer.MAX_VALUE;
        Grass closestGrass = null;
        for (Grass grass : grasses) {
            int distance = Coordinates.calculateDistance(herbivore.getCoordinates(), grass.getCoordinates());
            if (distance < minDistance) {
                minDistance = distance;
                closestGrass = grass;
            }
        }
        return closestGrass;
    }

    private void askForSingleMove() {
        moveInProgress = false;
        System.out.println();
        System.out.println("Хотите сделать ещё один ход?");
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
