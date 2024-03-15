package com.example;

import com.example.entities.Entity;
import com.example.entities.Herbivore;
import com.example.entities.Predator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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


    public void nextTurn() {
        Mapping mapping = actions.getCurrentMap();

        while (true) {

            List<Predator> allPredators = Mapping.findAllPredators(mapping);
            List<Herbivore> allHerbivore = Mapping.findAllHerbivore(mapping);

            for (int i = 0; i < allHerbivore.size(); i++) {
                while (true) {
                    List<Coordinates> pathPredator = allPredators.get(i).searchPath(
                            allPredators.get(i).getCoordinates(),
                            allHerbivore.get(i).getCoordinates(), mapping, allPredators.get(i));

                    Coordinates oldCoordinates = allPredators.get(i).getCoordinates();

                    allPredators.get(i).makeMove(oldCoordinates,
                            allHerbivore.get(i).getCoordinates(), pathPredator, mapping);

                    Coordinates newCoordinates = allPredators.get(i).getCoordinates();

                    if (!oldCoordinates.equals(newCoordinates)) {
                        renderMap(mapping);
                        System.out.println("===");
                    }else{
                        break;
                    }

                }
            }
            if(allHerbivore.isEmpty()){
                break;
            }
        }
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
