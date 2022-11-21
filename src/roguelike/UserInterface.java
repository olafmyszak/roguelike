package roguelike;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    private final DungeonMap dungeonMap;
    private final Player player;
    private final Monster monster;

    public UserInterface(int length, int height) {
        this.scanner = new Scanner(System.in);
        this.dungeonMap = new DungeonMap(length,height);
        player = dungeonMap.getPlayer();
        monster = dungeonMap.getMonster();
    }

    public void run(){
        String command = null;
        while  (!Objects.equals(command, "q")){
            clearScreen();
            dungeonMap.createGrid();
            dungeonMap.printGrid();
            command = scanner.next();
            player.move(command);
            dungeonMap.simplePathfinding(monster);
        }
    }

    public void clearScreen(){
       try{
           final String os = System.getProperty("os.name");

           if(os.contains("Windows")){
               new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
           }
           else{
               Runtime.getRuntime().exec("clear");
           }
       } catch (IOException | InterruptedException e) {
           throw new RuntimeException(e);
       }
    }
}
