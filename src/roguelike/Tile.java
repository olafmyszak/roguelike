package roguelike;

public class Tile {
    private final SquareType squareType;

    public Tile(Symbols symbol) {
        squareType = new SquareType(symbol);
    }

    public boolean isAbleToMoveOnThisTile(){
        return switch (squareType.getCharacterSymbol()) {
            case "-" -> true;
            default -> false;
        };
    }

    public String getCharacterSymbol(){
       return squareType.getCharacterSymbol();
    }
}
