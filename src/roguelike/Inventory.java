package roguelike;


public class Inventory {
    private final Item[] storage;
    private final int storageSize = 9;
    private int currentSlotInStorage = 0;

    public Inventory() {
        this.storage = new Item[storageSize];
    }

    public void addItemToInventory(Item item) {
        if (!isFull()) {
            storage[currentSlotInStorage] = new Item(item);
            ++currentSlotInStorage;
        } else {
            System.out.println("Inventory is full!");
        }
    }

    public void dropItem(int index) {
        if (index > 0 && index <= storageSize) {
            storage[index - 1] = null;
            --currentSlotInStorage;
        } else {
            System.out.println("No such slot.");
        }
    }

    public Item[] getStorage() {
        return storage;
    }

    public void printInventory() {
        if (!isEmpty()) {
            System.out.print("|");
            for (Item item : storage) {
                if (item != null)
                    System.out.print("&|");
            }
            System.out.println();
        }
    }

    public boolean isFull() {
        return currentSlotInStorage == storageSize;
    }

    public boolean isEmpty() {
        return currentSlotInStorage == 0;
    }
}