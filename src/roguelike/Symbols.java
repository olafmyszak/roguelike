package roguelike;

public enum Symbols {
    WALL,
    UNBREAKABLE_WALL,
    FLOOR,
    PLAYER,

    ITEM,
    DOOR,
    BOSS,
    VAMPIRE,
    GHOST,
    ZOMBIE;

    public static String getCharacterSymbol(Symbols symbol) {
        return switch (symbol) {
            case WALL, UNBREAKABLE_WALL -> "#";
            case FLOOR -> "-";
            case PLAYER -> "@";
            case ITEM -> "&";
            case DOOR -> "+";
            case BOSS -> "B";
            case ZOMBIE -> "Z";
            case VAMPIRE -> "V";
            case GHOST -> "G";
        };
    }
}