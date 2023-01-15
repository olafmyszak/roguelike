package roguelike;

public class Tile {
    private final SquareType squareType;
    private Item item;

    public Tile(Symbols symbol, String name, String description) {
        squareType = new SquareType(symbol, name, description);
    }

    public Tile(Symbols symbol) {
        this.squareType = new SquareType(symbol);
    }

    public Tile(Symbols symbol, String name) {
        this.squareType = new SquareType(symbol, name);
    }

    public boolean isAbleToMoveOnThisTile() {
        return switch (squareType.getCharacterSymbol()) {
            case "-" -> true;
            default -> false;
        };
    }

    public String getCharacterSymbol() {
        return squareType.getCharacterSymbol();
    }
}
