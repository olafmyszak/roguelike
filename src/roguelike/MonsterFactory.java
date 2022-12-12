package roguelike;

public abstract class MonsterFactory {
    public static GameCharacter getMonster(MonsterType monsterType, int length, int height, int level){
        return switch (monsterType){
            case VAMPIRE -> new Vampire(length, height, level);
            case SKELETON -> new Skeleton(length, height, level);
            case ZOMBIE ->  new Zombie(length, height, level);
            case GHOST -> new Ghost(length, height, level);
        };
    }
}
