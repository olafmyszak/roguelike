package roguelike;

public class Player extends GameCharacter {
    private String previousCommand;
    private int experience;
    private Inventory inventory;

    public Player(int length, int height, String name, String description, Attribute healthPoints, Attribute speed, Attribute strength, Attribute intelligence, Attribute dexterity, Attribute mana, int level, Inventory inventory) {
        super(length, height, name, description, healthPoints, speed, strength, intelligence, dexterity, mana, level);
        this.inventory = inventory;
    }

    public Player(int length, int height, String name) {
        super(length, height, name, "You. The hero.", new Attribute(30), new Attribute(2), new Attribute(5), new Attribute(5), new Attribute(5), new Attribute(20), 1);
        this.inventory = new Inventory();
    }

    @Override
    public void action(String command) {
        previousCommand = command;
        super.action(command);
    }

    public void pickUpItem(Item item) {
        inventory.addItemToInventory(item);
    }

    public void reverseMove() {
        switch (previousCommand) {
            case "w" -> setCoordinates(coordinates.getX() + 1, getCoordinates().getY());
            case "s" -> setCoordinates(coordinates.getX() - 1, getCoordinates().getY());
            case "a" -> setCoordinates(coordinates.getX(), getCoordinates().getY() + 1);
            case "d" -> setCoordinates(coordinates.getX(), getCoordinates().getY() - 1);
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
}