package dungeon;

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

    public Point randomPoint(int xMin, int yMin, int xMax, int yMax){
        Random random = new Random();
        int x = random.nextInt(xMin, xMax);
        int y = random.nextInt(yMin, yMax);
        return new Point(x,y);
    }
}
