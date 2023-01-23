package roguelike;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level {
    private final DungeonMap dungeonMap;
    private final List<GameCharacter> monsterList;
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
        simplePathfinding(monsterList, player.getX(), player.getY());
        dungeonMap.createGrid(player, monsterList, itemList);
        dungeonMap.printGrid();

        for (Point itemCoordinate : itemCoordinates) {
            if (itemCoordinate.equals(player.coordinates)) {
                int index = itemCoordinates.indexOf(itemCoordinate);
                Item item = itemList.get(index);

                if (!player.getInventory().isFull()) {
                    player.pickUpItem(item);
                    item.getCoordinates().nullCoordinates();
                } else {
                    System.out.print("Inventory is full.");
                }
            }
        }

        for (GameCharacter monster : monsterList) {
            Tile[] neighbours = dungeonMap.getNeighbours(monster.getCoordinates());

            for (Tile neighbour : neighbours) {
                if(neighbour.getCharacterSymbol().equals(Symbols.getCharacterSymbol(Symbols.PLAYER))){
                    monster.attack(player);
                }
            }
        }

        System.out.println(player.basicAttributes.getHealthPoints().getCurrent());
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

        ItemFactory itemFactory = new ItemFactory(maxLength, maxHeight);

        int percent = new Random().nextInt(0, 100);

        if (percent > 50 && percent <= 85) {
            itemList.add(itemFactory.getRandomItem());
        } else if (percent > 85) {
            itemList.add(itemFactory.getRandomItem());
            itemList.add(itemFactory.getRandomItem());
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