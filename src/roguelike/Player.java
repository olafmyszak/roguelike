package roguelike;

import javax.swing.*;

public class Player extends GameCharacter {
    private final Inventory inventory;
    private String previousCommand;
    private int experience;

    public Player(Point coordinates, String name) {
        super(coordinates, name, "You. The hero.", new Attribute(30), new Attribute(1), new Attribute(5), new Attribute(5), new Attribute(5), new Attribute(20), 1);
        this.inventory = new Inventory();
    }

    @Override
    public void action(String command) {
        if (command.toLowerCase().matches("drop+\\s\\d+")) {
            int index = Integer.parseInt(command.replaceAll("[^0-9]", ""));
            inventory.dropItem(index);
        } else {
            previousCommand = command;
            super.action(command);
        }
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

    public void printStats(){
        System.out.printf("HP: %d SPD: %d STR: %d INT: %d DXT: %d MAN: %d\n", healthPoints.getCurrent(), speed.getCurrent(), strength.getCurrent(), intelligence.getCurrent(), dexterity.getCurrent(), mana.getCurrent());
    }
}