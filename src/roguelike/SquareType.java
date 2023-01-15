package roguelike;

public class SquareType {
    private final Symbols symbol;
    private String name;
    private String description;

    public SquareType(Symbols symbol) {
        this.symbol = symbol;
    }

    public SquareType(Symbols symbol, String name, String description) {
        this.symbol = symbol;
        this.name = name;
        this.description = description;
    }

    public SquareType(Symbols symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public String getCharacterSymbol() {
        return switch (symbol) {
            case WALL -> "#";
            case FLOOR -> "-";
            case PLAYER -> "@";
            case MONSTER -> "?";
            case ITEM -> "&";
        };
    }
}


