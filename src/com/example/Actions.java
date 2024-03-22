package com.example;

import java.io.IOException;
import java.util.Scanner;

public class Actions {
    private Settings settings = new Settings();
    private Scanner scanner = new Scanner(System.in);;
    private Simulation simulation;
    private Mapping currentMap;
    public static int speed;
    private static String mapSize = "";
    public static String speedSize = "";
    public static String creatureMovementMode = "";
    public Actions(){
        this.simulation = new Simulation(this);
    }
    public void initActions() throws InterruptedException, IOException {
        settings.loadSettingsFromFile();
        greet();
        requestSpeedFromUser();
        currentMap = Settings.createMap(Integer.parseInt(mapSize));
        currentMap.fillRandomPositions(mapSize);
        speed = Settings.calculateSpeed(Integer.parseInt(speedSize));
        setCurrentMap(currentMap);
        simulation.renderMap(currentMap);
        chooseMovementForCreature();
    }

    public Mapping getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(Mapping currentMap) {
        this.currentMap = currentMap;
    }

    private void greet(){
        System.out.println("Здравствуйте! Выберите размер карты: ");
        System.out.println("1 - средняя, 2 - большая, 3 - стандартная");
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

    private void chooseMovementForCreature() throws InterruptedException {
        System.out.println("Выберите режим перемещения: ");
        System.out.println("1 - запустить цикл автоматического перемещения, 2 - одноразовое перемещение");
        while (true){
            creatureMovementMode = scanner.nextLine();
            switch (creatureMovementMode){
                case "1":
                    this.simulation.startSimulation();
                    return;
                case "2":
                    this.simulation.nextTurn();
                    return;
                default:
                    System.out.println("Пожалуйста, выберите один из предложенных вариантов");
                    break;
            }
        }
    }
    private void requestSpeedFromUser(){
        System.out.println("Выберите скорость перемещения: ");
        System.out.println("1 - стандартная, 2 - замедлинная, 3 - медлинная");
        while(true){
            speedSize = scanner.nextLine();
            switch (speedSize) {
                case "1", "2", "3":
                    return;
                default:
                    System.out.println("Пожалуйста, выберите один из предложенных вариантов");
                    break;
            }
        }
    }
}
