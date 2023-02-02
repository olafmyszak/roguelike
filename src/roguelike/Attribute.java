package roguelike;

public class Attribute {
    private int base;
    private int current;

    public Attribute(int base) {
        this.base = base;
        this.current = base;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int value) {
        current = value;
    }
}