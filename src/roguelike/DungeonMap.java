package roguelike;

import java.util.List;

public class DungeonMap {
    private final int length;
    private final int height;
    private final Tile[][] tiles;

    public DungeonMap(int length, int height) {
        this.length = length;
        this.height = height;
        this.tiles = new Tile[length][height];
    }
    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public void createGrid(Player player, List<Monster> monsters) {
        for (int cols = 0; cols < height; ++cols) {
            tiles[0][cols] = new Tile(Symbols.WALL);
            tiles[length - 1][cols] = new Tile(Symbols.WALL);
        }

        for (int rows = 0; rows < length; ++rows) {
            tiles[rows][0] = new Tile(Symbols.WALL);
            tiles[rows][height - 1] = new Tile(Symbols.WALL);
        }

        for (int i = 1; i < length - 1; ++i) {
            for (int j = 1; j < height - 1; ++j) {
                tiles[i][j] = new Tile(Symbols.FLOOR);
            }
        }

        drawPlayer(player);
        drawMonster(monsters);
    }

    private void drawPlayer(Player player) {
        int x = player.getX();
        int y = player.getY();

        if (!tiles[x][y].isAbleToMoveOnThisTile()) {
            player.reverseMove();
        }

        x = player.getX();
        y = player.getY();

        tiles[x][y] = new Tile(Symbols.PLAYER);
    }

    private void drawMonster(List<Monster> monsters) {
        for (Monster monster : monsters) {
            int x = monster.getX();
            int y = monster.getY();

            tiles[x][y] = new Tile(Symbols.MONSTER);
        }
    }

    public void printGrid() {
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < height; ++j) {
                System.out.print(tiles[i][j].getCharacterSymbol() + " ");
            }
            System.out.println();
        }
    }
}