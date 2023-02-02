package roguelike;

public class Tile {
    private final SquareType squareType;

    public Tile(Symbols symbol, String name, String description) {
        squareType = new SquareType(symbol, name, description);
    }

    public Tile(Symbols symbol, String name) {
        this.squareType = new SquareType(symbol, name);
    }

    public boolean isAbleToMoveOnThisTile() {
        return squareType.getSymbol() == Symbols.FLOOR || squareType.getSymbol() == Symbols.ITEM || squareType.getSymbol() == Symbols.DOOR;
    }

    public String getCharacterSymbol() {
        return squareType.getCharacterSymbol();
    }

    public Symbols getSymbol(){
        return squareType.getSymbol();
    }

}