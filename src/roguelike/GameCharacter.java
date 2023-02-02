package roguelike;

import java.util.ArrayList;
import java.util.List;

public class GameCharacter {
    protected Point coordinates;
    protected String name;
    protected String description;
    protected Attribute healthPoints;
    protected double speed;
    protected int damage;
    protected Attribute mana;
    protected int level;
    protected State state;

    public GameCharacter(Point coordinates, String name, String description, Attribute healthPoints, double speed, int damage, Attribute mana, int level) {
        this.coordinates = coordinates;
        this.name = name;
        this.description = description;
        this.healthPoints = healthPoints;
        this.speed = speed;
        this.damage = damage;
        this.mana = mana;
        this.level = level;
        this.state = State.ALIVE;
    }

    public Attribute getHealthPoints() {
        return healthPoints;
    }

    public void setCurrentHealthPoints(int healthPoints) {
        this.healthPoints.setCurrent(healthPoints);
    }

    public double getSpeed() {
        return speed;
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
        character.takeDamage(damage);
    }

    public State getState() {
        return state;
    }

    private void takeDamage(int damage) {
        int hp = healthPoints.getCurrent();

        if (damage >= hp) {
            setCurrentHealthPoints(0);
            this.state = State.DEAD;
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