package roguelike;

public class BasicAttributes {
    protected Attribute healthPoints;
    protected Attribute speed;
    protected Attribute strength;
    protected Attribute intelligence;
    protected Attribute dexterity;
    protected Attribute mana;

    public Attribute getHealthPoints() {
        return healthPoints;
    }

    public void setCurrentHealthPoints(int healthPoints) {
        this.healthPoints.setCurrent(healthPoints);
    }

    public Attribute getSpeed() {
        return speed;
    }

    public Attribute getStrength() {
        return strength;
    }

    public Attribute getIntelligence() {
        return intelligence;
    }

    public Attribute getDexterity() {
        return dexterity;
    }

    public Attribute getMana() {
        return mana;
    }

    public BasicAttributes(Attribute healthPoints, Attribute speed, Attribute strength, Attribute intelligence, Attribute dexterity, Attribute mana) {
        this.healthPoints = healthPoints;
        this.speed = speed;
        this.strength = strength;
        this.intelligence = intelligence;
        this.dexterity = dexterity;
        this.mana = mana;
    }
}
