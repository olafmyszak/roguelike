package roguelike;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Level {
    private final DungeonMap dungeonMap;
    private final List<Monster> monsterList;
    private final List<Item> itemList;
    private final List<Point> itemCoordinates;
    private final int currentLevel;
    private List<Point> walkableTiles;

    public Level(DungeonMap dungeonMap, int currentLevel) {
        this.dungeonMap = dungeonMap;
        this.monsterList = new ArrayList<>();
        this.itemList = new ArrayList<>();
        this.itemCoordinates = new ArrayList<>();
        this.currentLevel = currentLevel;
    }

    public List<Point> getWalkableTiles() {
        return walkableTiles;
    }

    public void start() {
        dungeonMap.createGrid();
        walkableTiles = dungeonMap.getWalkableTiles();
        boolean bossIsSpawned = false;


        if (currentLevel >= 10) {
            double random = Math.random();

            if (random < 0.35) {
                spawnBoss();
                bossIsSpawned = true;
            }
        }

        if (!bossIsSpawned) {
            generateMonsters();
            generateItems();

            for (Item item : itemList) {
                itemCoordinates.add(item.getCoordinates());
            }
        }
    }

    public Code run(Player player) {
        dungeonMap.printGrid(player, monsterList, itemList);

        Point playerCoordinates = player.getCoordinates();

        for (Iterator<Point> pointIterator = itemCoordinates.iterator(); pointIterator.hasNext(); ) {
            Point next = pointIterator.next();
            int index = itemCoordinates.indexOf(next);
            Item item = itemList.get(index);

            if (next.equals(playerCoordinates)) {
                player.pickUpItem(item);
                itemList.remove(index);
                pointIterator.remove();
            }
        }

        if (dungeonMap.getTile(playerCoordinates).getSymbol() == Symbols.DOOR) {
            return Code.NEXT_LEVEL;
        }

        return Code.RUNNING;
    }

    private void generateMonsters() {
        int numberOfMonsters = (int) (walkableTiles.size() * 0.005);
        int monsterLevel = new Random().nextInt(currentLevel, currentLevel + 2);

        MonsterFactory monsterFactory = new MonsterFactory(walkableTiles, monsterLevel);

        for (int i = 0; i < numberOfMonsters; ++i) {
            Monster monster = monsterFactory.getRandomMonster();
            monsterList.add(monster);
        }
    }

    private void generateItems() {
        ItemFactory itemFactory = new ItemFactory(dungeonMap.getWalkableTiles());

        int percent = new Random().nextInt(0, 100);

        if (percent > 50 && percent <= 85) {
            itemList.add(itemFactory.getRandomItem());
        } else if (percent > 85) {
            itemList.add(itemFactory.getRandomItem());
            itemList.add(itemFactory.getRandomItem());
        }
    }

    private void spawnBoss() {
        monsterList.add(new Boss(walkableTiles.get(new Random().nextInt(walkableTiles.size()))));
    }

    public List<Monster> getMonsterList() {
        return monsterList;
    }

    public void removeMonster(Monster monster) {
        monsterList.remove(monster);
    }

    public DungeonMap getDungeonMap() {
        return dungeonMap;
    }
}