package com.example;

import java.util.Scanner;

public class Actions {
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
    public void initActions() throws InterruptedException {
        greet();
        requestSpeedFromUser();
        currentMap = Settings.createMap(Integer.parseInt(mapSize));
        currentMap.fillRandomPositions(mapSize);
        speed = Settings.calculateSpeed(Integer.parseInt(speedSize));
        setCurrentMap(currentMap);
        chooseMovementForCreature();
    }

    public Mapping getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(Mapping currentMap) {
        this.currentMap = currentMap;
    }

    private void greet(){
        System.out.println("Привет! Выбери размер карты");
        System.out.println("1 - средней, 2 - большой, 3 - стандартный");
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
        System.out.println("Выберите режим передвижения существ");
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
        System.out.println("Выбери скорость существам");
        System.out.println("1 - стандартная, 2 - средняя, 3 - медлинная");
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
