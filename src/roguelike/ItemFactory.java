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
    private final List<Point> coordinates;

    public ItemFactory(List<Point> coordinates) {
        this.coordinates = coordinates;
        this.items = new ArrayList<>();
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
                double speed = Double.parseDouble(values[3]);
                int damage = Integer.parseInt(values[4]);
                int mana = Integer.parseInt(values[5]);

                items.add(new Item(null, name, description, new Attribute(healthPoints), speed, damage, new Attribute(mana)));

                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Item getRandomItem() {
        Point point = coordinates.get(new Random().nextInt(coordinates.size()));
        Item item = new Item(items.get(new Random().nextInt(items.size())));
        item.setCoordinates(point);

        return item;
    }
}