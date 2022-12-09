package roguelike;

import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    private final DungeonMap dungeonMap;
    private final Player player;
    private final Monster monster;

    public UserInterface(int length, int height, String name) {
        this.scanner = new Scanner(System.in);
        this.dungeonMap = new DungeonMap(length, height, name);
        player = dungeonMap.getPlayer();
        monster = dungeonMap.getMonster();
    }

    public void run() {
        String command;
        do {
            clearScreen();
            dungeonMap.createGrid();
            dungeonMap.printGrid();
            command = scanner.next();
            player.move(command);
            dungeonMap.simplePathfinding(monster);
        } while (!command.equals("q"));
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