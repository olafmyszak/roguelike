package roguelike;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemFactory {
    private final List<Item> items;
    private final int length;
    private final int height;
    private List<Point> usedCoordinates;

    public ItemFactory(int length, int height) {
        this.length = length;
        this.height = height;
        items = new ArrayList<>();
        loadItems();
    }

    private void loadItems() {
        Path path = Paths.get("items.csv");

        try (BufferedReader bufferedReader = Files.newBufferedReader(path, StandardCharsets.US_ASCII)) {
            bufferedReader.readLine();
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] values = line.split(";");

                String name = values[0];
                String description = values[1];
                int healthPoints = Integer.parseInt(values[2]);
                int speed = Integer.parseInt(values[3]);
                int strength = Integer.parseInt(values[4]);
                int intelligence = Integer.parseInt(values[5]);
                int dexterity = Integer.parseInt(values[6]);
                int mana = Integer.parseInt(values[7]);

                String[] slotsAsString = values[8].split(",");

                List<PlayerSlots> slotsList = new ArrayList<>();

                for (String s : slotsAsString) {
                    slotsList.add(PlayerSlots.valueOf(s));
                }

                PlayerSlots[] slots = new PlayerSlots[slotsList.size()];
                slots = slotsList.toArray(slots);

                items.add(new Item(length, height, name, description, slots, new Attribute(healthPoints), new Attribute(speed), new Attribute(strength), new Attribute(intelligence), new Attribute(dexterity), new Attribute(mana)));

                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Item getRandomItem() {
        Item item = items.get(new Random().nextInt(items.size()));

        boolean flag = false;

        while (!flag) {
            for (Point usedCoordinate : usedCoordinates) {
                if (item.getCoordinates().equals(usedCoordinate)) {
                    item = items.get(new Random().nextInt(items.size()));
                }
                flag = true;
            }
        }
        usedCoordinates.add(item.getCoordinates());

        return item;
    }
}