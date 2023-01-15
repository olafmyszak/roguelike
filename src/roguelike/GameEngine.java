package roguelike;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class GameEngine {
    private final int length;
    private final int height;
    private final int currentLevel = 0;
    private final Player player;
    private final ArrayList<Level> levels;
    private final PriorityQueue<GameCharacter> eventQueue;

    public GameEngine(int length, int height, String name) {
        this.length = length;
        this.height = height;
        this.player = new Player(length, height, name);
        this.levels = new ArrayList<>();
        this.eventQueue = new PriorityQueue<>((a, b) -> b.basicAttributes.speed.getCurrent() - a.basicAttributes.speed.getCurrent());
    }

    public void run(String command) {
        player.move(command);
        levels.get(currentLevel).run(player);
    }

    public void start() {
        levels.add(new Level(new DungeonMap(length, height), "name", "desc"));
        levels.get(0).start(player, 0);

        eventQueue.add(player);
        eventQueue.addAll(levels.get(0).getMonsterList());
    }

    public void fight() {

    }
}