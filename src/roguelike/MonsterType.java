package roguelike;

public enum MonsterType {
    VAMPIRE,
    SKELETON,
    ZOMBIE,
    GHOST;

    public static MonsterType getRandom(){
        return values()[(int) (Math.random() * values().length)];
    }
}
