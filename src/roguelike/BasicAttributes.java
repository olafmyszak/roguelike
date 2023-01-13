package roguelike;

public class BasicAttributes {
    protected Attribute healthPoints;
    protected Attribute speed;
    protected Attribute strength;
    protected Attribute intelligence;
    protected Attribute dexterity;
    protected Attribute mana;

    public BasicAttributes(Attribute healthPoints, Attribute speed, Attribute strength, Attribute intelligence, Attribute dexterity, Attribute mana) {
        this.healthPoints = healthPoints;
        this.speed = speed;
        this.strength = strength;
        this.intelligence = intelligence;
        this.dexterity = dexterity;
        this.mana = mana;
    }
}
