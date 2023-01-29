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
    private List<Point> walkableTiles;

    public Level(DungeonMap dungeonMap, String name, String description) {
        this.dungeonMap = dungeonMap;
        this.monsterList = new ArrayList<>();
        this.itemList = new ArrayList<>();
        this.itemCoordinates = new ArrayList<>();
    }

    public List<Point> getWalkableTiles() {
        return walkableTiles;
    }

    public void start(int currentLevel) {
        dungeonMap.createGrid();
        generateMonsters(currentLevel);
        generateItems();
        this.walkableTiles = dungeonMap.getWalkableTiles();
//        player = new Player(walkableTiles.get(new Random().nextInt(walkableTiles.size())), name);
        //dungeonMap.printGrid(player, monsterList, itemList);

        for (Item item : itemList) {
            itemCoordinates.add(item.getCoordinates());
        }
    }

    public void run(Player player) {
        dungeonMap.printGrid(player, monsterList, itemList);

        for(Iterator<Point> pointIterator = itemCoordinates.iterator(); pointIterator.hasNext(); ){
            Point next = pointIterator.next();
            int index = itemCoordinates.indexOf(next);
            Item item = itemList.get(index);

            if(next.equals(player.coordinates)){
                player.pickUpItem(item);
                itemList.remove(index);
                pointIterator.remove();
            }
        }
    }

    private void generateMonsters(int currentLevel) {
        final int minNumberOfMonsters = 1;
        final int maxNumberOfMonsters = 3;

        int numberOfMonsters = new Random().nextInt(minNumberOfMonsters, maxNumberOfMonsters);
        int monsterLevel = new Random().nextInt(currentLevel, currentLevel+2);

        MonsterFactory monsterFactory = new MonsterFactory(dungeonMap.getWalkableTiles(), monsterLevel);

        for (int i = 0; i < numberOfMonsters; ++i) {
            monsterList.add(monsterFactory.getRandomMonster());
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

    public List<Monster> getMonsterList() {
        return monsterList;
    }
}