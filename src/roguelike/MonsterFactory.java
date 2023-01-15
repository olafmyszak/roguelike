package roguelike;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class MonsterFactory {
    private final ArrayList<GameCharacter> monsters;
    private final int length;
    private final int height;
    private final int level;

    public MonsterFactory(int length, int height, int level) {
        this.length = length;
        this.height = height;
        this.level = level;
        monsters = new ArrayList<>();
        loadMonsters();
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
                int speed = Integer.parseInt(values[3]);
                int strength = Integer.parseInt(values[4]);
                int intelligence = Integer.parseInt(values[5]);
                int dexterity = Integer.parseInt(values[6]);
                int mana = Integer.parseInt(values[7]);

                monsters.add(new GameCharacter(length, height, name, description, new Attribute(healthPoints), new Attribute(speed), new Attribute(strength), new Attribute(intelligence), new Attribute(dexterity), new Attribute(mana), level));

                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GameCharacter getRandomMonster() {
        return monsters.get(new Random().nextInt(monsters.size()));
    }
}
