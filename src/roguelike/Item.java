package roguelike;

public class Item {
    private final String name;
    private final String description;
    private final PlayerSlots[] goodSlots;
    private final BasicAttributes basicAttributes;
    private final Point coordinates;

    public Item(int length, int height, String name, String description, PlayerSlots[] goodSlots, Attribute healthPoints, Attribute speed, Attribute strength, Attribute intelligence, Attribute dexterity, Attribute mana) {
        this.name = name;
        this.description = description;
        this.goodSlots = goodSlots;
        this.basicAttributes = new BasicAttributes(healthPoints, speed, strength, intelligence, dexterity, mana);
        this.coordinates = Point.randomPoint(1, 1, length - 1, height - 1);
    }

    public Item(Point coordinates, String name, String description, PlayerSlots[] goodSlots, Attribute healthPoints, Attribute speed, Attribute strength, Attribute intelligence, Attribute dexterity, Attribute mana) {
        this.name = name;
        this.description = description;
        this.goodSlots = goodSlots;
        this.basicAttributes = new BasicAttributes(healthPoints, speed, strength, intelligence, dexterity, mana);
        this.coordinates = coordinates;
    }

    public int getX() {
        return coordinates.getX();
    }
    public int getY() {
        return coordinates.getY();
    }
}