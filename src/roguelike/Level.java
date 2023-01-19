package roguelike;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level {
    private final DungeonMap dungeonMap;
    private final List<GameCharacter> monsterList;
    private final List<Item> itemList;

    public Level(DungeonMap dungeonMap, String name, String description) {
        this.dungeonMap = dungeonMap;
        this.monsterList = new ArrayList<>();
        this.itemList = new ArrayList<>();
    }

    public void start(Player player, int currentLevel) {
        generateMonsters(currentLevel);
        generateItems();
        dungeonMap.createGrid(player, monsterList, itemList);
        dungeonMap.printGrid();
    }

    public void run(Player player) {
        simplePathfinding(monsterList, player.getX(), player.getY());
        dungeonMap.createGrid(player, monsterList, itemList);
        dungeonMap.printGrid();
    }

    private void generateMonsters(int currentLevel) {
        final int maxLength = dungeonMap.getLength();
        final int maxHeight = dungeonMap.getHeight();
        final int minNumberOfMonsters = 1;
        final int maxNumberOfMonsters = 3;

        int numberOfMonsters = new Random().nextInt(minNumberOfMonsters, maxNumberOfMonsters);

        MonsterFactory monsterFactory = new MonsterFactory(maxLength, maxHeight, currentLevel);

        for (int i = 0; i < numberOfMonsters; ++i) {
            monsterList.add(monsterFactory.getRandomMonster());
        }
    }

    private void generateItems() {
        final int maxLength = dungeonMap.getLength();
        final int maxHeight = dungeonMap.getHeight();

        PossibleItems possibleItems = new PossibleItems(maxLength, maxHeight);

        int percent = new Random().nextInt(0, 100);

        if(percent > 50 && percent <=85){
            itemList.add(possibleItems.getRandom());
        }
        else if(percent > 85){
            itemList.add(possibleItems.getRandom());
            itemList.add(possibleItems.getRandom());
        }
    }

    public void simplePathfinding(List<GameCharacter> monsters, int playerX, int playerY) {
        for (GameCharacter monster : monsters) {
            Point monsterCoordinates = monster.getCoordinates();
            int monsterX = monsterCoordinates.getX();
            int monsterY = monsterCoordinates.getY();

            if (monsterX > playerX) {
                monster.action("w");
            } else if (monsterX < playerX) {
                monster.action("s");
            } else if (monsterY > playerY) {
                monster.action("a");
            } else {
                monster.action("d");
            }
        }
    }

    public List<GameCharacter> getMonsterList() {
        return monsterList;
    }
}