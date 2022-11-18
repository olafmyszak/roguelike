package roguelike;

public class SquareType {
    private final Symbols symbol;

    public SquareType(Symbols symbol) {
        this.symbol = symbol;
    }

    public String getCharacterSymbol(){
        return switch (symbol) {
            case WALL -> "#";
            case FLOOR -> "-";
            case PLAYER -> "@";
            case MONSTER -> "?";
        };
    }
}


