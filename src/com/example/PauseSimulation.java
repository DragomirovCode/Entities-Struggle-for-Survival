package com.example;

import java.util.Scanner;

public class PauseSimulation extends Thread {
    Scanner scanner = new Scanner(System.in);
    public static boolean running = true;
    public void stopTread() {
        running = false;
        scanner.close();
    }
    public void run() {
        while (running) {
            String tread = scanner.nextLine();
            if (tread.equals("0")) {
                System.out.println("Симуляция завершена.");
                stopTread();
            }
        }
    }
}
