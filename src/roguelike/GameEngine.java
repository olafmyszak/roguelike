package roguelike;

import java.util.*;

public class GameEngine {
    private final int length;
    private final int height;
    private final int currentLevel = 0;
    private final Player player;
    private final ArrayList<Level> levels;
    private final Queue<GameCharacter> eventQueue;


    public GameEngine(int length, int height, String name) {
        this.length = length;
        this.height = height;
        this.player = new Player(length, height, name);
        this.levels = new ArrayList<>();
        this.eventQueue = new PriorityQueue<>();
    }

    public void run(String command) {
        player.move(command);
        levels.get(currentLevel).run(player);
    }

    public void start(){
        levels.add( new Level(new DungeonMap(length, height), "lol", "lol"));
        levels.get(0).start(player);
    }

}
