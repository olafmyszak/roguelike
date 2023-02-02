package roguelike;

import java.util.*;

public class GameEngine {
    private final int maxLength;
    private final int maxHeight;
    private final ArrayList<Level> levels;
    private final PriorityQueue<GameCharacter> eventQueue;
    private int currentLevel = 1;
    private Player player;

    public GameEngine(int length, int height) {
        this.maxLength = length;
        this.maxHeight = height;

        this.levels = new ArrayList<>();

        this.eventQueue = new PriorityQueue<>(Comparator.comparingDouble(a -> a.speed));

    }

    public Code run(String command) {
        PriorityQueue<GameCharacter> tempQueue = new PriorityQueue<>(Comparator.comparingDouble(a -> a.speed));

        while (!eventQueue.isEmpty()) {
            GameCharacter currentActor = eventQueue.poll();

            int turns = (int) currentActor.speed;

            if (turns == 0) {
                turns = 1;
            }

            for (int i = 0; i < turns; ++i) {
                if (currentActor.getClass() == Monster.class) {
                    handleMonster((Monster) currentActor);
                } else {
                    switch (command) {
                        case "i" -> {
                            List<Monster> monsterList = levels.get(currentLevel - 1).getMonsterList();

                            for (Monster monster : monsterList) {
                                if (player.coordinates.isUp(monster.coordinates)) {
                                    player.attack(monster);
                                }
                            }
                        }
                        case "j" -> {
                            List<Monster> monsterList = levels.get(currentLevel - 1).getMonsterList();

                            for (Monster monster : monsterList) {
                                if (player.coordinates.isLeft(monster.coordinates)) {
                                    player.attack(monster);
                                }
                            }
                        }
                        case "k" -> {
                            List<Monster> monsterList = levels.get(currentLevel - 1).getMonsterList();

                            for (Monster monster : monsterList) {
                                if (player.coordinates.isDown(monster.coordinates)) {
                                    player.attack(monster);
                                }
                            }
                        }
                        case "l" -> {
                            List<Monster> monsterList = levels.get(currentLevel - 1).getMonsterList();

                            for (Monster monster : monsterList) {
                                if (player.coordinates.isRight(monster.coordinates)) {
                                    player.attack(monster);
                                }
                            }
                        }
                        case "break left" -> {
                            if (levels.get(currentLevel - 1).getDungeonMap().getTile(player.coordinates.left()).getSymbol() == Symbols.WALL) {
                                player.breakWAll();
                                levels.get(currentLevel - 1).getDungeonMap().floorAWall(player.coordinates.left());
                            }
                        }
                        case "break up" -> {
                            if (levels.get(currentLevel - 1).getDungeonMap().getTile(player.coordinates.up()).getSymbol() == Symbols.WALL) {
                                player.breakWAll();
                                levels.get(currentLevel - 1).getDungeonMap().floorAWall(player.coordinates.up());
                            }
                        }
                        case "break down" -> {
                            if (levels.get(currentLevel - 1).getDungeonMap().getTile(player.coordinates.down()).getSymbol() == Symbols.WALL) {
                                player.breakWAll();
                                levels.get(currentLevel - 1).getDungeonMap().floorAWall(player.coordinates.down());
                            }
                        }
                        case "break right" -> {
                            if (levels.get(currentLevel - 1).getDungeonMap().getTile(player.coordinates.right()).getSymbol() == Symbols.WALL) {
                                player.breakWAll();
                                levels.get(currentLevel - 1).getDungeonMap().floorAWall(player.coordinates.right());
                            }
                        }

                        default -> player.action(command);
                    }
                }
            }

            if (player.getState() == State.DEAD) {
                return Code.GAME_OVER;
            }

            if (currentActor.getState() == State.DEAD) {
                levels.get(currentLevel - 1).removeMonster((Monster) currentActor);
            } else {
                tempQueue.add(currentActor);
            }
        }

        eventQueue.addAll(tempQueue);

        Code code = levels.get(currentLevel - 1).run(player);

        if (code == Code.NEXT_LEVEL) {
            ++currentLevel;
            int length = new Random().nextInt(20, maxLength);
            int height = new Random().nextInt(20, maxHeight);

            levels.add(new Level(new DungeonMap(length, height), "name", "desc"));
            levels.get(currentLevel - 1).start(currentLevel);

            Point point = levels.get(currentLevel - 1).getWalkableTiles().get(new Random().nextInt(levels.get(currentLevel - 1).getWalkableTiles().size()));
            player.setCoordinates(point.getX(), point.getY());

            eventQueue.addAll(levels.get(currentLevel - 1).getMonsterList());
            levels.get(currentLevel - 1).run(player);
        }

        player.getInventory().printInventory();
        player.printStats();
        player.regenerateMana();

        return code;
    }

    private void handleMonster(Monster monster) {
        Point monsterCoordinates = monster.getCoordinates();
        Point playerCoordinates = player.getCoordinates();

        if (monsterCoordinates.isNeighbour(playerCoordinates)) {
            monster.attack(player);
        } else {
            monster.moveTowardsPlayer(playerCoordinates);
        }
    }

    public void start(String name) {
        int length = new Random().nextInt(20, maxLength);
        int height = new Random().nextInt(20, maxHeight);
        levels.add(new Level(new DungeonMap(length, height), "name", "desc"));
        levels.get(currentLevel - 1).start(currentLevel);

        Point point = levels.get(currentLevel - 1).getWalkableTiles().get(new Random().nextInt(levels.get(currentLevel - 1).getWalkableTiles().size()));
        player = new Player(point, name, 5);

        eventQueue.add(player);
        eventQueue.addAll(levels.get(currentLevel - 1).getMonsterList());

        levels.get(currentLevel - 1).run(player);
        player.getInventory().printInventory();
        player.printStats();
    }
}