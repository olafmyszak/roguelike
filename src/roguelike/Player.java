package roguelike;

public class Player extends GameCharacter {
    private final Inventory inventory;
    private final int manaRegen;
    private String previousCommand;
    private int experience;
    private int score;

    public Player(Point coordinates, String name, int manaRegen) {
        super(coordinates, name, "You. The hero.", new Attribute(100), 1, 10, new Attribute(20), 1);
        this.inventory = new Inventory();
        this.manaRegen = manaRegen;
        this.score = 0;
        this.experience = 0;
    }

    @Override
    public void action(String command) {
        if (command.matches("drop+\\s\\d+")) {
            int index = Integer.parseInt(command.replaceAll("[^0-9]", ""));
            removeItemStats(inventory.getStorage()[index - 1]);
            inventory.dropItem(index);
        } else {
            previousCommand = command;
            super.action(command);
        }
    }

    public void pickUpItem(Item item) {
        inventory.addItemToInventory(item);
        addItemStats(item);
        score += 20;
    }

    private void addItemStats(Item item) {
        mana.setBase(mana.getBase() + item.getMana().getCurrent());
        mana.setCurrent(mana.getBase());
        damage += item.getDamage();
        speed += item.getSpeed();
        healthPoints.setCurrent(healthPoints.getCurrent() + item.getHealthPoints().getCurrent());
    }

    private void removeItemStats(Item item) {
        mana.setBase(mana.getBase() - item.getMana().getCurrent());
        mana.setCurrent(mana.getBase());
        damage -= item.getDamage();
        speed -= item.getSpeed();
        healthPoints.setCurrent(healthPoints.getCurrent() - item.getHealthPoints().getCurrent());
    }

    public void breakWAll() {
        if (mana.getCurrent() >= 10) {
            mana.setCurrent(mana.getCurrent() - 10);
        } else {
            String x = "Not enough mana!";
            System.out.println(x);
        }
    }

    private void scaleAttributesByLevel() {
        healthPoints.setBase((int) (healthPoints.getBase() * (level / 4.0 + 1)));
        healthPoints.setCurrent((int) (healthPoints.getCurrent() + healthPoints.getBase() * 0.2));
        damage *= (level / 10.0 + 1);
        mana.setBase((int) (mana.getBase() * (level / 20.0 + 1)));
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void regenerateMana() {
        mana.setCurrent(mana.getCurrent() + manaRegen);

        if (mana.getCurrent() > mana.getBase()) {
            mana.setCurrent(mana.getBase());
        }
    }

    public void gainExperience(int value){
        experience += value;
    }

    @Override
    public void attack(GameCharacter character) {
        experience += 10;
        super.attack(character);
    }

    public void checkLevelUp() {
        if (experience >= level * 100) {
            experience = 0;
            ++level;
            scaleAttributesByLevel();
        }
    }

    public String progressBar() {
        int count = experience / (level * 10);

        StringBuilder stringBuilder = new StringBuilder("[          ]");

        for (int i = 1; i < count; i++) {
            stringBuilder.replace(i, i + 1, "#");
        }

        return stringBuilder.toString();
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