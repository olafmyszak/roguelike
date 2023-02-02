package roguelike;

public class Item {
    private final String name;
    private final String description;
    private final Attribute healthPoints;
    private final double speed;
    private final int damage;
    private final Attribute mana;
    private Point coordinates;

    public Item(Item item) {
        this.name = item.name;
        this.description = item.description;
        this.healthPoints = item.healthPoints;
        this.speed = item.speed;
        this.damage = item.damage;
        this.mana = item.mana;
        this.coordinates = item.coordinates;
    }

    public Item(Point coordinates, String name, String description, Attribute healthPoints, double speed, int damage, Attribute mana) {
        this.name = name;
        this.description = description;
        this.healthPoints = healthPoints;
        this.speed = speed;
        this.damage = damage;
        this.mana = mana;
        this.coordinates = coordinates;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
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