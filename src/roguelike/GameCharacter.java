package roguelike;

public abstract class GameCharacter {
    protected Point coordinates;
    protected String name;
    protected String description;
    protected Attribute healthPoints;
    protected Attribute speed;
    protected Attribute strength;
    protected Attribute intelligence;
    protected Attribute dexterity;
    protected Attribute mana;
    protected int level;

    protected abstract void move(String command);

    public Point getCoordinates(){
        return coordinates;
    }

    public void setCoordinates(int x, int y) {
        coordinates.setX(x);
        coordinates.setY(y);
    }
}