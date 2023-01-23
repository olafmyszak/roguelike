package roguelike;

public enum Symbols {
    WALL,
    FLOOR,
    PLAYER,
    MONSTER,
    ITEM;

    public static String getCharacterSymbol(Symbols symbol) {
        return switch (symbol) {
            case WALL -> "#";
            case FLOOR -> "-";
            case PLAYER -> "@";
            case MONSTER -> "?";
            case ITEM -> "&";
        };
    }
}
