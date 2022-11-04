package roguelike;

import java.util.Objects;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    private final DungeonMap dungeonMap;
    private final Player player;

    public UserInterface(int length, int height) {
        this.scanner = new Scanner(System.in);
        this.dungeonMap = new DungeonMap(length,height);
        player = dungeonMap.getPlayer();
    }

    public void run(){
        String command = null;
        while  (!Objects.equals(command, "q")){
            dungeonMap.createGrid();
            dungeonMap.printGrid();
            command = scanner.next();
            player.move(command);
        }
    }
}
