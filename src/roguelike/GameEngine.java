package roguelike;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class GameEngine {
    private final DungeonMap dungeonMap;
    private final int currentLevel;
    private final Player player;
    private final List<Monster> monsterList;
    private final Queue<GameCharacter> eventQueue;

    public GameEngine(DungeonMap dungeonMap, int currentLevel, Player player) {
        this.dungeonMap = dungeonMap;
        this.currentLevel = currentLevel;
        this.player = player;
        this.monsterList = new ArrayList<>();
        this.eventQueue = new PriorityQueue<>();
    }

    public void run(){

    }
}
