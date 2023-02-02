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

        System.out.println("Current dungeon level: " + currentLevel);

        while (!eventQueue.isEmpty()) {
            GameCharacter currentActor = eventQueue.poll();

            if (currentActor.getClass() == Player.class) {
                playerTurn(command);
            } else {

                handleMonster((Monster) currentActor);
            }

            if (player.getState() == State.DEAD) {
                return Code.GAME_OVER;
            }

            if (currentActor.getState() == State.BOSS_DEAD) {
                player.setScore(player.getScore() + 100);
                return Code.GAME_WON;
            }

            if (currentActor.getState() == State.DEAD) {
                assert currentActor instanceof Monster;
                levels.get(currentLevel - 1).removeMonster((Monster) currentActor);
                player.setScore(player.getScore() + 50);
                player.gainExperience(20);
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

            levels.add(new Level(new DungeonMap(length, height), currentLevel));
            levels.get(currentLevel - 1).start();

            Point point = levels.get(currentLevel - 1).getWalkableTiles().get(new Random().nextInt(levels.get(currentLevel - 1).getWalkableTiles().size()));
            player.setCoordinates(point.getX(), point.getY());

            eventQueue.addAll(levels.get(currentLevel - 1).getMonsterList());
            levels.get(currentLevel - 1).run(player);
        }

        player.getInventory().printInventory();
        player.printStats();
        player.regenerateMana();
        System.out.println("Character level: " + player.getLevel() + "           EXP: " + player.progressBar());

        return code;
    }

    public int getFinalScore() {
        return player.getScore();
    }

    public void playerTurn(String command) {
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

        player.checkLevelUp();
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
        System.out.println("Current dungeon level: " + currentLevel);

        int length = new Random().nextInt(20, maxLength);
        int height = new Random().nextInt(20, maxHeight);
        levels.add(new Level(new DungeonMap(length, height), currentLevel));
        levels.get(currentLevel - 1).start();

        Point point = levels.get(currentLevel - 1).getWalkableTiles().get(new Random().nextInt(levels.get(currentLevel - 1).getWalkableTiles().size()));
        player = new Player(point, name, 5);

        eventQueue.add(player);
        eventQueue.addAll(levels.get(currentLevel - 1).getMonsterList());

        levels.get(currentLevel - 1).run(player);
        player.getInventory().printInventory();
        player.printStats();
        System.out.println("Character level: " + player.getLevel() + "           EXP: " + player.progressBar());
    }
}