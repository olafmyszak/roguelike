package roguelike;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level {
    private final DungeonMap dungeonMap;
    private final List<GameCharacter> monsterList;
    private final String name;
    private final String description;

    public Level(DungeonMap dungeonMap, String name, String description) {
        this.dungeonMap = dungeonMap;
        this.monsterList = new ArrayList<>();
        this.name = name;
        this.description = description;
    }

    public void start(Player player, int currentLevel){
        generateMonsters(currentLevel);
        dungeonMap.createGrid(player, monsterList);
        dungeonMap.printGrid();
    }

    public void run(Player player){
        simplePathfinding(monsterList, player.getX(), player.getY());
        dungeonMap.createGrid(player, monsterList);
        dungeonMap.printGrid();
    }

    private void generateMonsters(int currentLevel){
        final int maxLength = dungeonMap.getLength();
        final int maxHeight = dungeonMap.getHeight();
        final int minNumberOfMonsters = 2;
        final int maxNumberOfMonsters = 5;

        int numberOfMonsters = new Random().nextInt(minNumberOfMonsters, maxNumberOfMonsters);

        for(int i=0; i<numberOfMonsters; ++i) {
            monsterList.add(MonsterFactory.getMonster(MonsterType.getRandom(), maxLength, maxHeight, currentLevel));
        }
    }

    public void simplePathfinding(List<GameCharacter> monsters, int playerX, int playerY){
        for (GameCharacter monster : monsters) {
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

    public List<GameCharacter> getMonsterList() {
        return monsterList;
    }
}