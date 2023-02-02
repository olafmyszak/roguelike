package roguelike;

public class SquareType {
    private final Symbols symbol;
    private final String name;
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

    public Symbols getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}