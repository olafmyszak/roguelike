package roguelike;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> wearableSlots;
    private final Item[] storage;
    private final int storageSize = 9;
    private int currentSlotInStorage = 0;

    public Inventory() {
        this.wearableSlots = new ArrayList<>(PlayerSlots.getSize());
        this.storage = new Item[storageSize];
    }

    public List<Item> getWearableSlots() {
        return wearableSlots;
    }

    public void addItemToInventory(Item item) {
        storage[currentSlotInStorage] = item;
        ++currentSlotInStorage;
    }

    public boolean isFull() {
        return currentSlotInStorage == 9;
    }
}