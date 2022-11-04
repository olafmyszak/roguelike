package roguelike;

public class Player {
    private final Point coordinates;
    private String previousCommand;

    public Player(int length, int height){
        coordinates = new Point().randomPoint(1, 1, length-1, height-1);
    }

    public Point getCoordinates(){
        return coordinates;
    }

    public void setCoordinates(int x, int y) {
        coordinates.setX(x);
        coordinates.setY(y);
    }

    public void move(String command){
        previousCommand = command;
        switch (command){
            case "w" -> setCoordinates(coordinates.getX()-1, getCoordinates().getY());
            case "s" -> setCoordinates(coordinates.getX()+1, getCoordinates().getY());
            case "a" -> setCoordinates(coordinates.getX(), getCoordinates().getY()-1);
            case "d" -> setCoordinates(coordinates.getX(), getCoordinates().getY()+1);
        }
    }

    public void reverseMove(){
        switch (previousCommand){
            case "w" -> setCoordinates(coordinates.getX()+1, getCoordinates().getY());
            case "s" -> setCoordinates(coordinates.getX()-1, getCoordinates().getY());
            case "a" -> setCoordinates(coordinates.getX(), getCoordinates().getY()+1);
            case "d" -> setCoordinates(coordinates.getX(), getCoordinates().getY()-1);
        }
    }
}
