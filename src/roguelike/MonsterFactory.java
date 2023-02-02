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

public class MonsterFactory {
    private final List<Monster> monsters;
    private final List<Point> coordinates;
    private final int level;

    public MonsterFactory(List<Point> coordinates, int level) {
        this.coordinates = coordinates;
        this.level = level;
        this.monsters = new ArrayList<>();
        this.loadMonsters();
    }


    private void loadMonsters() {
        Path path = Paths.get("monsters.csv");

        try (BufferedReader bufferedReader = Files.newBufferedReader(path, StandardCharsets.US_ASCII)) {
            bufferedReader.readLine(); //pomijamy pierwsza linijke

            String line = bufferedReader.readLine();

            while (line != null) {
                String[] values = line.split(";");

                String name = values[0];
                String description = values[1];
                int healthPoints = Integer.parseInt(values[2]);
                double speed = Double.parseDouble(values[3]);
                int damage = Integer.parseInt(values[4]);
                int mana = Integer.parseInt(values[5]);

                monsters.add(new Monster(null, name, description, new Attribute(healthPoints), speed, damage, new Attribute(mana), level));

                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Monster getRandomMonster() {
        Point point = coordinates.get(new Random().nextInt(coordinates.size()));
        Monster monster = new Monster(monsters.get(new Random().nextInt(monsters.size())));
        monster.setCoordinates(point);

        return monster;
    }
}