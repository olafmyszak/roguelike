package dungeon;

import java.util.Objects;

public class Grid {
    private final int length;
    private final int height;
    private final Player player;
    private final Point coordinates;

    private final String[][] grid;

    public Grid(int length, int height) {
        this.length = length;
        this.height = height;
        this.player = new Player(length, height);
        this.coordinates = player.getCoordinates();
        this.grid = new String[length][height];
    }

    public Player getPlayer() {
        return player;
    }

    public void createGrid() {
        for (int cols = 0; cols < height; ++cols) {
            grid[0][cols] = "#";
            grid[length - 1][cols] = "#";
        }

        for (int rows = 0; rows < length; ++rows) {
            grid[rows][0] = "#";
            grid[rows][height - 1] = "#";
        }

        for (int i = 1; i < length - 1; ++i) {
            for (int j = 1; j < height - 1; ++j) {
                grid[i][j] = "-";
            }
        }

        drawPlayer();
    }

    boolean wallDetection(Point point){
        int x = point.getX();
        int y = point.getY();

        return Objects.equals(grid[x][y], "#");
    }

    void drawPlayer(){
        if(wallDetection(coordinates)){
           player.reverseMove();
        }

        int x = coordinates.getX();
        int y = coordinates.getY();

        grid[x][y] = "@";
    }

    public void printGrid() {
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < height; ++j) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

}