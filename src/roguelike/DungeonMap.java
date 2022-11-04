package roguelike;

public class DungeonMap {
    private final int length;
    private final int height;
    private final Player player;
    private final Point coordinates;
    private final Tile[][] tiles;

    public DungeonMap(int length, int height) {
        this.length = length;
        this.height = height;
        this.player = new Player(length, height);
        this.coordinates = player.getCoordinates();
        this.tiles = new Tile[length][height];
    }

    public Player getPlayer() {
        return player;
    }

    public void createGrid() {
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

        drawPlayer();
    }

    void drawPlayer() {
        int x = coordinates.getX();
        int y = coordinates.getY();

        if (!tiles[x][y].isAbleToMoveOnThisTile()) {
            player.reverseMove();
        }

        x = coordinates.getX();
        y = coordinates.getY();

        tiles[x][y] = new Tile(Symbols.PLAYER);
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