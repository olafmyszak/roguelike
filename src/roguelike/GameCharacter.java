package roguelike;

import java.util.ArrayList;
import java.util.List;

public class GameCharacter {
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
    protected List<Ability> abilities;

    public GameCharacter(int length, int height, String name, String description, Attribute healthPoints, Attribute speed, Attribute strength, Attribute intelligence, Attribute dexterity, Attribute mana, int level) {
        this.coordinates = Point.randomPoint(1, 1, length - 1, height - 1);
        this.name = name;
        this.description = description;
        this.healthPoints = healthPoints;
        this.speed = speed;
        this.strength = strength;
        this.intelligence = intelligence;
        this.dexterity = dexterity;
        this.mana = mana;
        this.level = level;
        this.abilities = new ArrayList<>();

    }

    public GameCharacter(int length, int height, String name, String description, Attribute healthPoints, Attribute speed, Attribute strength, Attribute intelligence, Attribute dexterity, Attribute mana, int level, List<Ability> abilities) {
        this.coordinates = Point.randomPoint(1, 1, length - 1, height - 1);
        this.name = name;
        this.description = description;

        this.level = level;
        this.abilities = abilities;
    }

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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    protected void move(String command) {
        command = command.toLowerCase();
        switch (command) {
            case "w" -> coordinates.setX(coordinates.getX() - 1);
            case "s" -> coordinates.setX(coordinates.getX() + 1);
            case "a" -> coordinates.setY(coordinates.getY() - 1);
            case "d" -> coordinates.setY(coordinates.getY() + 1);
        }
    }

    protected void attack(GameCharacter character) {
        int damage = strength.getCurrent() * level / 5;

        character.takeDamage(damage);
    }

    private void takeDamage(int damage) {
        int hp = healthPoints.getCurrent();

        if (damage >= hp) {
            setCurrentHealthPoints(0);
        } else {
            setCurrentHealthPoints(hp - damage);
        }
    }

    public void action(String command) {
        command = command.toLowerCase();

        switch (command) {
            case "w", "a", "s", "d" -> move(command);
        }
    }


    public Point getCoordinates() {
        return coordinates;
    }

    public int getX() {
        return coordinates.getX();
    }

    public int getY() {
        return coordinates.getY();
    }

    public void setCoordinates(int x, int y) {
        coordinates.setX(x);
        coordinates.setY(y);
    }
}