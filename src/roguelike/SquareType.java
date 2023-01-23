package roguelike;

public class SquareType {
    private final Symbols symbol;
    private String name;
    private String description;

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
        return Symbols.getCharacterSymbol(symbol);
    }
}