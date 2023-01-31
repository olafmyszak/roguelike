package roguelike;

import java.util.*;

public class GameEngine {
    private final int length;
    private final int height;

    private final int currentLevel = 1;
    private final ArrayList<Level> levels;
    private final PriorityQueue<GameCharacter> eventQueue;
    private Player player;

    public GameEngine(int length, int height) {
        this.length = length;
        this.height = height;

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

                        default -> player.action(command);
                    }
                }
            }

            if (player.getState() == State.DEAD) {
                return Code.GAME_OVER;
            }

            if (currentActor.getState() == State.DEAD) {
                levels.get(currentLevel-1).removeMonster((Monster) currentActor);
            }
            else {
                tempQueue.add(currentActor);
            }
        }

        eventQueue.addAll(tempQueue);

        levels.get(currentLevel - 1).run(player);
        player.getInventory().printInventory();
        player.printStats();

        return Code.RUNNING;
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
        levels.add(new Level(new DungeonMap(length, height), "name", "desc"));
        levels.get(0).start(1);

        Point point = levels.get(0).getWalkableTiles().get(new Random().nextInt(levels.get(0).getWalkableTiles().size()));
        player = new Player(point, name);

        eventQueue.add(player);
        eventQueue.addAll(levels.get(0).getMonsterList());

        levels.get(0).run(player);
        player.getInventory().printInventory();
        player.printStats();
    }
}