package roguelike;

public class Attribute {
    private final int base;
    private final int current;

    public Attribute(int base, int current) {
        this.base = base;
        this.current = current;
    }

    public Attribute(int base){
        this.base = base;
        this.current = base;
    }
}