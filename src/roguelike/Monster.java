package roguelike;

public class Monster extends GameCharacter {

    public Monster(Point coordinates, String name, String description, Attribute healthPoints, double speed, int strength, Attribute mana, int level) {
        super(coordinates, name, description, healthPoints, speed, strength, mana, level);
    }

    public void moveTowardsPlayer(Point playerCoordinates) {
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
    }
}