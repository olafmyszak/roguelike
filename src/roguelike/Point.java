package roguelike;

import java.util.Objects;
import java.util.Random;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        x = 0;
        y = 0;
    }

    public static Point randomPoint(int xMin, int yMin, int xMax, int yMax) {
        Random random = new Random();
        int x = random.nextInt(xMin, xMax);
        int y = random.nextInt(yMin, yMax);
        return new Point(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public boolean equals(Point point) {
        return x == point.x && y == point.y;
    }

    public void nullCoordinates() {
        x = -1;
        y = -1;
    }

    public boolean isNeighbour(Point point){
        Point[] neighbours = new Point[4];

        neighbours[0] = new Point(x+1, y);
        neighbours[1] = new Point(x-1, y);
        neighbours[2] = new Point(x, y+1);
        neighbours[3] = new Point(x, y-1);

        for (Point neighbour : neighbours) {
            if(point.equals(neighbour)){
                return true;
            }
        }

        return false;
    }
}