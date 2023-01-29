package roguelike;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

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
        if(!isFull()) {
            storage[currentSlotInStorage] = item;
            ++currentSlotInStorage;
        }
        else {
            System.out.println("Inventory is full!");
        }
    }

    public Item[] getStorage() {
        return storage;
    }

    public void printInventory(){
        System.out.print("||");
        for (Item item : storage) {
            if(item != null)
                System.out.print("|&|");
        }
        System.out.println("||");
    }

    public boolean isFull() {
        return currentSlotInStorage == storageSize;
    }
}