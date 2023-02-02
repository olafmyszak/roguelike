package roguelike;

public class Player extends GameCharacter {
    private final Inventory inventory;
    private String previousCommand;
    private int experience;
    private int manaRegen;

    public Player(Point coordinates, String name, int manaRegen) {
        super(coordinates, name, "You. The hero.", new Attribute(1000), 1, 10000, new Attribute(20), 1);
        this.inventory = new Inventory();
        this.manaRegen = manaRegen;
    }

    @Override
    public void action(String command) {
        if (command.matches("drop+\\s\\d+")) {
            int index = Integer.parseInt(command.replaceAll("[^0-9]", ""));
            removeItemStats(inventory.getStorage()[index]);
            inventory.dropItem(index);
        } else {
            previousCommand = command;
            super.action(command);
        }
    }

    public void pickUpItem(Item item) {
        inventory.addItemToInventory(item);
        addItemStats(item);
    }

    private void addItemStats(Item item){
        mana.setCurrent(mana.getCurrent() + item.getMana().getCurrent());
        damage += item.getDamage();
        speed += item.getSpeed();
        healthPoints.setCurrent(healthPoints.getCurrent() + item.getHealthPoints().getCurrent());
    }

    private void removeItemStats(Item item){
        mana.setBase(mana.getBase() + item.getMana().getCurrent());
        mana.setCurrent(mana.getBase());
        damage -= item.getDamage();
        speed -= item.getSpeed();
        healthPoints.setCurrent(healthPoints.getCurrent() - item.getHealthPoints().getCurrent());
    }

    public void breakWAll(){
        if(mana.getCurrent() >= 10) {
            mana.setCurrent(mana.getCurrent() - 10);
        }
        else {
            System.out.println("Not enough mana!");
        }
    }

    public void regenerateMana(){
        mana.setCurrent(mana.getCurrent() + manaRegen);

        if(mana.getCurrent() > mana.getBase()){
            mana.setCurrent(mana.getBase());
        }
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

    public void printStats() {
        String speed = String.format(String.valueOf(this.speed)).replaceAll("\\.0+$", ""); //gets rid of trailing zeroes
        System.out.printf("HP: %d SPD: %s DMG: %d MAN: %d\n", healthPoints.getCurrent(), speed, damage, mana.getCurrent());
    }
}