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

    public void createGrid(Player player, List<GameCharacter> monsters, List<Item> items) {
        for (int cols = 0; cols < height; ++cols) {
            tiles[0][cols] = new Tile(Symbols.WALL, "Wall");
            tiles[length - 1][cols] = new Tile(Symbols.WALL, "Wall");
        }

        for (int rows = 0; rows < length; ++rows) {
            tiles[rows][0] = new Tile(Symbols.WALL, "Wall");
            tiles[rows][height - 1] = new Tile(Symbols.WALL, "Wall");
        }

        for (int i = 1; i < length - 1; ++i) {
            for (int j = 1; j < height - 1; ++j) {
                tiles[i][j] = new Tile(Symbols.FLOOR, "Floor");
            }
        }

        drawItems(items);
        drawMonster(monsters);
        drawPlayer(player);
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

    private void drawMonster(List<GameCharacter> monsters) {
        for (GameCharacter monster : monsters) {
            int x = monster.getX();
            int y = monster.getY();

            if(!tiles[x][y].isAbleToMoveOnThisTile()){
                monster.setCoordinates(x+1, y);
            }

            if(!tiles[x][y].isAbleToMoveOnThisTile()){
                monster.setCoordinates(x-1, y);
            }

            if(!tiles[x][y].isAbleToMoveOnThisTile()){
                monster.setCoordinates(x, y+1);
            }

            if(!tiles[x][y].isAbleToMoveOnThisTile()){
                monster.setCoordinates(x, y-1);
            }

            tiles[x][y] = new Tile(Symbols.MONSTER, monster.getName(), monster.getDescription());
        }
    }

    public Tile[] getNeighbours(Point coordinates){
        int x = coordinates.getX();
        int y = coordinates.getY();

        Tile[] result = new Tile[4];

        result[0] = tiles[x+1][y];
        result[1] = tiles[x-1][y];
        result[2] = tiles[x][y+1];
        result[3] = tiles[x-1][y-1];

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

    public void printGrid() {
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < height; ++j) {
                System.out.print(tiles[i][j].getCharacterSymbol() + " ");
            }
            System.out.println();
        }
    }

    public void clearTile(Point point) {
        int x = point.getX();
        int y = point.getY();

        tiles[x][y] = new Tile(Symbols.FLOOR, "Floor");
    }
}