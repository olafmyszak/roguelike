package roguelike;

public class Item {
    private final String name;
    private final String description;
    private final PlayerSlots[] goodSlots;
    private final BasicAttributes basicAttributes;
    private final Point coordinates;

    public Item(String name, String description, int length, int height, PlayerSlots[] goodSlots, Attribute healthPoints, Attribute speed, Attribute strength, Attribute intelligence, Attribute dexterity, Attribute mana) {
        this.name = name;
        this.description = description;
        this.goodSlots = goodSlots;
        this.basicAttributes = new BasicAttributes(healthPoints, speed, strength, intelligence, dexterity, mana);
        this.coordinates = new Point(length, height);
    }

    public Item(String name, String description, Point coordinates, PlayerSlots[] goodSlots, Attribute healthPoints, Attribute speed, Attribute strength, Attribute intelligence, Attribute dexterity, Attribute mana) {
        this.name = name;
        this.description = description;
        this.goodSlots = goodSlots;
        this.basicAttributes = new BasicAttributes(healthPoints, speed, strength, intelligence, dexterity, mana);
        this.coordinates = coordinates;
    }

    public int getX(){
        return coordinates.getX();
    }

    public int getY(){
        return coordinates.getY();
    }
}