package roguelike;

import java.util.EnumSet;

public class Inventory {
    private final EnumSet<PlayerSlots> wearableSlots;
    private final Item[] storage;
    private final int storageSize = 9;
    private int currentSlotInStorage = 0;

    public Inventory() {
        this.wearableSlots = EnumSet.allOf(PlayerSlots.class);
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
        } else {
            System.out.println("No such slot.");
        }
    }

    public Item[] getStorage() {
        return storage;
    }

    public void printInventory() {
        System.out.print("|");
        for (Item item : storage) {
            if (item != null)
                System.out.print("&|");
        }
    }

    public boolean isFull() {
        return currentSlotInStorage == storageSize;
    }
}