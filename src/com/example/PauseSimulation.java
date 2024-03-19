package com.example;

import java.util.Scanner;

public class PauseSimulation extends Thread {
    Scanner scanner = new Scanner(System.in);
    public static boolean running = true;
    public void stopTread() {
        running = false;
    }

    public void run() {
        while (running) {
            try {
                String tread = scanner.nextLine();
                if(tread.equals("0")){
                    System.out.println("Симуляция завершена.");
                    stopTread();
                }
                Thread.sleep(200); // Ждем некоторое время перед следующей итерацией
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
