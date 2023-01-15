package roguelike;

import java.util.ArrayList;
import java.util.List;

public class GameCharacter {
    protected Point coordinates;
    protected String name;
    protected String description;
    protected BasicAttributes basicAttributes;
    protected int level;
    protected List<Ability> abilities;
    public GameCharacter(int length, int height, String name, String description, Attribute healthPoints, Attribute speed, Attribute strength, Attribute intelligence, Attribute dexterity, Attribute mana, int level) {
        this.coordinates = Point.randomPoint(1, 1, length - 1, height - 1);
        this.name = name;
        this.description = description;
        this.basicAttributes = new BasicAttributes(healthPoints, speed, strength, intelligence, dexterity, mana);
        this.level = level;
        this.abilities = new ArrayList<>();
    }

    public GameCharacter(int length, int height, String name, String description, Attribute healthPoints, Attribute speed, Attribute strength, Attribute intelligence, Attribute dexterity, Attribute mana, int level, List<Ability> abilities) {
        this.coordinates = Point.randomPoint(1, 1, length - 1, height - 1);
        this.name = name;
        this.description = description;
        this.basicAttributes = new BasicAttributes(healthPoints, speed, strength, intelligence, dexterity, mana);
        this.level = level;
        this.abilities = abilities;
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