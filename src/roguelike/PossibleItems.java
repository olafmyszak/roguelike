package roguelike;

import java.util.Random;

public class PossibleItems {
    private final Item[] items;

    public PossibleItems(int length, int height) {
        this.items = new Item[] {new Item("Ring of Wisdom", "", Point.randomPoint(1, 1, length-1, height-1), new PlayerSlots[] {PlayerSlots.LEFT_FINGER, PlayerSlots.RIGHT_FINGER}, new Attribute(0), new Attribute(0), new Attribute(0), new Attribute(5), new Attribute(0), new Attribute(10)), new Item("Holy Cape", "", Point.randomPoint(1, 1, length-1, height-1), new PlayerSlots[] {PlayerSlots.RIGHT_HAND}, new Attribute(10), new Attribute(2), new Attribute(5), new Attribute(2), new Attribute(2), new Attribute(30))};
    }

    public Item getRandom(){
        return items[new Random().nextInt(items.length)];
    }
}
