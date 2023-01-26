package roguelike;

import java.util.List;

public class Monster extends GameCharacter{

    public Monster(int length, int height, String name, String description, Attribute healthPoints, Attribute speed, Attribute strength, Attribute intelligence, Attribute dexterity, Attribute mana, int level) {
        super(length, height, name, description, healthPoints, speed, strength, intelligence, dexterity, mana, level);
    }

    public Monster(int length, int height, String name, String description, Attribute healthPoints, Attribute speed, Attribute strength, Attribute intelligence, Attribute dexterity, Attribute mana, int level, List<Ability> abilities) {
        super(length, height, name, description, healthPoints, speed, strength, intelligence, dexterity, mana, level, abilities);
    }

    public void moveTowardsPlayer(Point playerCoordinates){
        int x = getX();
        int y = getY();

        int playerX = playerCoordinates.getX();
        int playerY = playerCoordinates.getY();

        if (x > playerX) {
            action("w");
        } else if (x < playerX) {
            action("s");
        } else if (y > playerY) {
            action("a");
        } else {
            action("d");
        }
        System.out.println("The " + getName() + " moves!");
    }
}