package com.example;

import java.io.IOException;
import java.util.Scanner;

public class Actions {
    private Settings settings = new Settings();
    private Scanner scanner = new Scanner(System.in);
    private Simulation simulation;
    private Mapping currentMap;
    public static String creatureMovementMode = "";
    public Actions(){
        this.simulation = new Simulation(this);
    }
    public void initActions() throws InterruptedException, IOException {
        settings.loadSettingsFromFile();
        currentMap = Settings.createMap();
        currentMap.fillRandomPositions(Settings.maxPredatorCount, Settings.maxHerbivoreCount, Settings.maxGrassCount,
                Settings.maxRockCount, Settings.maxPolypithCount, Settings.maxTreeCount);
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
}
