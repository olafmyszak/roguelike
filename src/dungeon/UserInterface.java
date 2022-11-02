package dungeon;

import java.util.Objects;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    private final Grid grid;
    private final Player player;

    public UserInterface(int length, int height) {
        this.scanner = new Scanner(System.in);
        this.grid = new Grid(length,height);
        player = grid.getPlayer();
    }

    public void run(){
        String command = null;
        while  (!Objects.equals(command, "q")){
            grid.createGrid();
            grid.printGrid();
            command = scanner.next();
            player.move(command);
        }
    }
}
