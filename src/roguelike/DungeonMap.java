package roguelike;

public class DungeonMap {
    private final int length;
    private final int height;
    private final Player player;
    private final Monster monster;
    private final Tile[][] tiles;

    public DungeonMap(int length, int height) {
        this.length = length;
        this.height = height;
        this.player = new Player(length, height);
        this.monster = new Monster(length, height);
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
        drawMonster();
    }

    private void drawPlayer() {
        int x = player.coordinates.getX();
        int y = player.coordinates.getY();

        if (!tiles[x][y].isAbleToMoveOnThisTile()) {
            player.reverseMove();
        }

        x = player.coordinates.getX();
        y = player.coordinates.getY();

        tiles[x][y] = new Tile(Symbols.PLAYER);
    }

    private void drawMonster(){
        int x = monster.coordinates.getX();
        int y = monster.coordinates.getY();

        tiles[x][y] = new Tile(Symbols.MONSTER);
    }


    public void printGrid() {
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < height; ++j) {
                System.out.print(tiles[i][j].getCharacterSymbol() + " ");
            }
            System.out.println();
        }
    }

    private class Pathfinding{
        private class Pair<T>{
            private T first;
            private T second;

            public Pair(T first, T second){
                this.first = first;
                this.second = second;
            }

            public T getSecond() {
                return second;
            }

            public void setFirst(T first) {
                this.first = first;
            }

            public T getFirst() {
                return first;
            }

            public void setSecond(T second) {
                this.second = second;
            }
        }




    }
}