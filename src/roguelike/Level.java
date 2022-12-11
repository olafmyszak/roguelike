package roguelike;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level {
    private final DungeonMap dungeonMap;
    private final List<Monster> monsterList;
    private final String name;
    private final String description;

    public Level(DungeonMap dungeonMap, String name, String description) {
        this.dungeonMap = dungeonMap;
        this.monsterList = new ArrayList<>();
        this.name = name;
        this.description = description;
    }

    public void start(Player player){
        generateMonsters();
        dungeonMap.createGrid(player, monsterList);
        dungeonMap.printGrid();
    }

    public void run(Player player){
        simplePathfinding(monsterList, player.getX(), player.getY());
        dungeonMap.createGrid(player, monsterList);
        dungeonMap.printGrid();
    }

    private void generateMonsters(){
        int maxLength = dungeonMap.getLength();
        int maxHeight = dungeonMap.getHeight();

        int numberOfMonsters = new Random().nextInt(2, 7);

        for(int i=0; i<numberOfMonsters; ++i)
        {
            monsterList.add(new Monster(maxLength, maxHeight));
        }
    }

    public void simplePathfinding(List<Monster> monsters, int playerX, int playerY){
        for (Monster monster : monsters) {
            Point monsterCoordinates = monster.getCoordinates();
            int monsterX = monsterCoordinates.getX();
            int monsterY = monsterCoordinates.getY();

            if (monsterX > playerX) {
                monster.move("w");
            } else if (monsterX < playerX) {
                monster.move("s");
            } else if (monsterY > playerY) {
                monster.move("a");
            } else {
                monster.move("d");
            }
        }
    }
}
