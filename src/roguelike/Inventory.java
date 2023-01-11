package roguelike;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> wearableSlots;
    private final Item[] storage;
    private final int storageSize = 9;

    public Inventory() {
        this.wearableSlots = new ArrayList<>(PlayerSlots.getSize());
        this.storage = new Item[storageSize];
    }

}