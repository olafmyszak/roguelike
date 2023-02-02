package roguelike;

public enum Symbols {
    WALL,
    UNBREAKABLE_WALL,
    FLOOR,
    PLAYER,
    MONSTER,
    ITEM,
    DOOR;

    public static String getCharacterSymbol(Symbols symbol) {
        return switch (symbol) {
            case WALL, UNBREAKABLE_WALL -> "#";
            case FLOOR -> "-";
            case PLAYER -> "@";
            case MONSTER -> "?";
            case ITEM -> "&";
            case DOOR -> "+";
        };
    }
}