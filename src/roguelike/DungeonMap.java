package roguelike;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DungeonMap {
    private final int length;
    private final int height;
    private final Tile[][] tiles;
    private final List<Point> walkableTiles;
    private final Tile[][] originalGrid;
    private int numberOfWalls;


    public DungeonMap(int length, int height) {
        this.length = length;
        this.height = height;
        this.tiles = new Tile[height][length];
        this.numberOfWalls = length * height;
        this.walkableTiles = new ArrayList<>();
        this.originalGrid = new Tile[length][height];
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public void createGrid() {
        drawInitialGrid();

        //filling the room with walkable rectangles until the percent of walls is less than 45
        int percentOfWalls = numberOfWalls * 100 / height * length;

        while (percentOfWalls > 45) {
            Point point = Point.randomPoint(1, 1, length - 1, height - 1);

            int length = new Random().nextInt(5, 10);
            int height = new Random().nextInt(5, 10);

            drawRectangle(point.getX(), point.getY(), length, height);
            percentOfWalls = numberOfWalls * 100 / height * length;
        }

        copyGrid();
    }

    private void copyGrid() {
        for (int i = 0; i < length; ++i) {
            if (height >= 0) System.arraycopy(tiles[i], 0, originalGrid[i], 0, height);
        }
    }

    private void drawInitialGrid() {
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < height; ++j) {
                tiles[i][j] = new Tile(Symbols.WALL, "Wall");
            }
        }
    }

    private void drawRectangle(int x, int y, int length, int height) {
        for (int i = x; i < length + x && i < this.length - 1; ++i) {
            for (int j = y; j < height + y && j < this.height - 1; ++j) {
                tiles[i][j] = new Tile(Symbols.FLOOR, "Floor");
                --numberOfWalls;
                walkableTiles.add(new Point(i, j));
            }
        }
    }

    private void drawPlayer(Player player) {
        int x = player.getX();
        int y = player.getY();

        if (!tiles[x][y].isAbleToMoveOnThisTile()) {
            player.reverseMove();
        }

        x = player.getX();
        y = player.getY();

        tiles[x][y] = new Tile(Symbols.PLAYER, player.getName(), player.getDescription());
    }

    private void drawMonster(List<Monster> monsters) {
        for (GameCharacter monster : monsters) {
            int x = monster.getX();
            int y = monster.getY();

            if (!tiles[x][y].isAbleToMoveOnThisTile()) {
                if (tiles[x + 1][y].isAbleToMoveOnThisTile()) {
                    ++x;
                } else if (tiles[x - 1][y].isAbleToMoveOnThisTile()) {
                    --x;
                } else if (!tiles[x][y + 1].isAbleToMoveOnThisTile()) {
                    ++y;
                } else if (!tiles[x][y - 1].isAbleToMoveOnThisTile()) {
                    --y;
                }

                monster.setCoordinates(x, y);
            }

            tiles[x][y] = new Tile(Symbols.MONSTER, monster.getName(), monster.getDescription());
        }
    }

    public Tile[] getNeighbours(Point coordinates) {
        int x = coordinates.getX();
        int y = coordinates.getY();

        Tile[] result = new Tile[4];

        result[0] = tiles[x + 1][y];
        result[1] = tiles[x - 1][y];
        result[2] = tiles[x][y + 1];
        result[3] = tiles[x - 1][y - 1];

        return result;
    }

    private void drawItems(List<Item> items) {
        for (Item item : items) {
            int x = item.getX();
            int y = item.getY();

            if (x != -1 && y != -1) {
                tiles[x][y] = new Tile(Symbols.ITEM, item.getName(), item.getDescription());
            }
        }
    }

    public void printGrid(Player player, List<Monster> monsters, List<Item> items) {
        drawItems(items);
        drawMonster(monsters);
        drawPlayer(player);

        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < height; ++j) {
                System.out.print(tiles[i][j].getCharacterSymbol() + " ");
            }
            System.out.println();
        }

        clearActorsAndProps();
    }

    private void clearActorsAndProps() {
        for (int i = 0; i < length; ++i) {
            if (height >= 0) System.arraycopy(originalGrid[i], 0, tiles[i], 0, height);
        }
    }

    public void clearTile(Point point) {
        int x = point.getX();
        int y = point.getY();

        tiles[x][y] = new Tile(Symbols.FLOOR, "Floor");
    }

    public List<Point> getWalkableTiles() {
        return walkableTiles;
    }
}