package roguelike;

public class Item {
    private final String name;
    private final String description;
    private final PlayerSlots[] goodSlots;
    private final BasicAttributes basicAttributes;
    private Point coordinates;

    public Point getCoordinates() {
        return coordinates;
    }

    public Item(Point coordinates, String name, String description, PlayerSlots[] goodSlots, Attribute healthPoints, Attribute speed, Attribute strength, Attribute intelligence, Attribute dexterity, Attribute mana) {
        this.name = name;
        this.description = description;
        this.goodSlots = goodSlots;
        this.basicAttributes = new BasicAttributes(healthPoints, speed, strength, intelligence, dexterity, mana);
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