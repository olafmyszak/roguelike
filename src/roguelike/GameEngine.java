package roguelike;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class GameEngine {
    private final int length;
    private final int height;

    private final int currentLevel = 1;
    private  Player player;
    private final ArrayList<Level> levels;
    private PriorityQueue<GameCharacter> eventQueue;

    public GameEngine(int length, int height) {
        this.length = length;
        this.height = height;

        this.levels = new ArrayList<>();

        this.eventQueue = new PriorityQueue<>((a, b) -> b.speed.getCurrent() - a.speed.getCurrent());

    }

    public void run(String command) {
        //player.action(command);

        PriorityQueue<GameCharacter> tempQueue = new PriorityQueue<>((a, b) -> b.speed.getCurrent() - a.speed.getCurrent());

        while (!eventQueue.isEmpty()) {
            GameCharacter currentActor = eventQueue.poll();


            for (int i = 0; i < currentActor.speed.getCurrent(); ++i) {
                if (currentActor.getClass() == Monster.class) {
                    handleMonster((Monster) currentActor);
                } else {
                    currentActor.action(command);
                }
            }

            tempQueue.add(currentActor);
        }

        eventQueue.addAll(tempQueue);

        levels.get(currentLevel-1).run(player);
        player.getInventory().printInventory();
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
        levels.get(0).start( 1);

        Point point = levels.get(0).getWalkableTiles().get(new Random().nextInt(levels.get(0).getWalkableTiles().size()));
        player = new Player(point, name);

        eventQueue.add(player);
        eventQueue.addAll(levels.get(0).getMonsterList());

        levels.get(0).run(player);
    }
}