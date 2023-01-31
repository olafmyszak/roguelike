package roguelike;

public class Item {
    private final String name;
    private final String description;
    private final PlayerSlots[] goodSlots;
    private Attribute healthPoints;
    private double speed;
    private Attribute strength;
    private Attribute intelligence;
    private Attribute dexterity;
    private Attribute mana;
    private Point coordinates;

    public Point getCoordinates() {
        return coordinates;
    }

    public Item(Point coordinates, String name, String description, PlayerSlots[] goodSlots, Attribute healthPoints, double speed, Attribute strength, Attribute intelligence, Attribute dexterity, Attribute mana) {
        this.name = name;
        this.description = description;
        this.goodSlots = goodSlots;
        this.healthPoints = healthPoints;
        this.speed = speed;
        this.strength = strength;
        this.intelligence = intelligence;
        this.dexterity = dexterity;
        this.mana = mana;
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getX() {
        return coordinates.getX();
    }

    public int getY() {
        return coordinates.getY();
    }
}