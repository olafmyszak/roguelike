package roguelike;

public class Item {
    private final String name;
    private final String description;
    //private final PlayerSlots[] goodSlots;
    private Attribute healthPoints;
    private double speed;
    private int damage;
    private Attribute mana;
    private Point coordinates;

    public Item(Item item) {
        this.name = item.name;
        this.description = item.description;
        //this.goodSlots = item.goodSlots;
        this.healthPoints = item.healthPoints;
        this.speed = item.speed;
        this.damage = item.damage;
        this.mana = item.mana;
        this.coordinates = item.coordinates;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public Item(Point coordinates, String name, String description , Attribute healthPoints, double speed, int damage, Attribute mana) {
        this.name = name;
        this.description = description;
        //this.goodSlots = goodSlots;
        this.healthPoints = healthPoints;
        this.speed = speed;
        this.damage = damage;
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

    public Attribute getHealthPoints() {
        return healthPoints;
    }

    public double getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

    public Attribute getMana() {
        return mana;
    }
}