package roguelike;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class GameEngine {
    private final int length;
    private final int height;


    private final int currentLevel = 0;
    private final Player player;
    private final ArrayList<Level> levels;
    private PriorityQueue<GameCharacter> eventQueue;

    public GameEngine(int length, int height, String name) {
        this.length = length;
        this.height = height;
        this.player = new Player(length, height, name);
        this.levels = new ArrayList<>();

        this.eventQueue = new PriorityQueue<>((a,b) -> b.speed.getCurrent() - a.speed.getCurrent());

    }

    public void run(String command) {
        //player.action(command);

        PriorityQueue<GameCharacter> tempQueue = new PriorityQueue<>((a,b) -> b.speed.getCurrent() - a.speed.getCurrent());

        while (!eventQueue.isEmpty()){
            GameCharacter currentActor = eventQueue.poll();



            for(int i=0; i<currentActor.speed.getCurrent(); ++i) {
                if (currentActor.getClass() == Monster.class) {
                    handleMonster((Monster) currentActor);

                } else {
                    player.action(command);
                }
            }

            tempQueue.add(currentActor);
        }

        eventQueue.addAll(tempQueue);

        levels.get(currentLevel).run(player);
    }

    private void handleMonster(Monster monster){
        Point monsterCoordinates = monster.getCoordinates();
        Point playerCoordinates = player.getCoordinates();

        if(monsterCoordinates.isNeighbour(playerCoordinates)){
            monster.attack(player);
        }
        else {
            monster.moveTowardsPlayer(playerCoordinates);
        }
    }
    public void start() {
        levels.add(new Level(new DungeonMap(length, height), "name", "desc"));
        levels.get(0).start(player, 0);

        eventQueue.add(player);
        eventQueue.addAll(levels.get(0).getMonsterList());
    }
}