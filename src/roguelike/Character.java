package roguelike;

public abstract class Character {
    protected Point coordinates;

    protected abstract void move(String command);

    public Point getCoordinates(){
        return coordinates;
    }

    public void setCoordinates(int x, int y) {
        coordinates.setX(x);
        coordinates.setY(y);
    }

}
