package roguelike;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level {
    private final DungeonMap dungeonMap;
    private final List<Monster> monsterList;
    private final List<Item> itemList;
    private final List<Point> itemCoordinates;

    public Level(DungeonMap dungeonMap, String name, String description) {
        this.dungeonMap = dungeonMap;
        this.monsterList = new ArrayList<>();
        this.itemList = new ArrayList<>();
        this.itemCoordinates = new ArrayList<>();
    }

    public void start(Player player, int currentLevel) {
        generateMonsters(currentLevel);
        generateItems();
        dungeonMap.createGrid(player, monsterList, itemList);
        dungeonMap.printGrid();

        for (Item item : itemList) {
            itemCoordinates.add(item.getCoordinates());
        }
    }

    public void run(Player player) {
        dungeonMap.createGrid(player, monsterList, itemList);
        dungeonMap.printGrid();

        for (Point itemCoordinate : itemCoordinates) {
            if (itemCoordinate.equals(player.coordinates)) {
                int index = itemCoordinates.indexOf(itemCoordinate);
                Item item = itemList.get(index);

                player.pickUpItem(item);
                itemList.remove(index);
            }
        }
    }

    private void generateMonsters(int currentLevel) {
        final int maxLength = dungeonMap.getLength();
        final int maxHeight = dungeonMap.getHeight();
        final int minNumberOfMonsters = 1;
        final int maxNumberOfMonsters = 3;

        int numberOfMonsters = new Random().nextInt(minNumberOfMonsters, maxNumberOfMonsters);
        int monsterLevel = new Random().nextInt(currentLevel, currentLevel+2);

        MonsterFactory monsterFactory = new MonsterFactory(maxLength, maxHeight, monsterLevel);

        for (int i = 0; i < numberOfMonsters; ++i) {
            monsterList.add(monsterFactory.getRandomMonster());
        }
    }

    private void generateItems() {
        final int maxLength = dungeonMap.getLength();
        final int maxHeight = dungeonMap.getHeight();

        ItemFactory itemFactory = new ItemFactory(maxLength, maxHeight);

        int percent = new Random().nextInt(0, 100);

        if (percent > 50 && percent <= 85) {
            itemList.add(itemFactory.getRandomItem());
        } else if (percent > 85) {
            itemList.add(itemFactory.getRandomItem());
            itemList.add(itemFactory.getRandomItem());
        }
    }

    public List<Monster> getMonsterList() {
        return monsterList;
    }
}