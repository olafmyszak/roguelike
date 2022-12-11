package roguelike;

import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
    private final GameEngine engine;

    public UserInterface(int length, int height, String name){
        this.engine = new GameEngine(length, height, name);
    }

    public void run() {
        String command;
        Scanner scanner = new Scanner(System.in);

        clearScreen();
        engine.start();
        do {
            command = scanner.next();
            clearScreen();
            engine.run(command);
        } while (!command.equalsIgnoreCase("q"));
    }

    public void clearScreen() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}