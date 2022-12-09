package roguelike;

public class Player extends GameCharacter {
    private String previousCommand;
    private int experience;

    public Player(int length, int height, String name, String description, Attribute healthPoints, Attribute speed, Attribute strength, Attribute intelligence, Attribute dexterity, Attribute mana, int level){
        super(name, description, healthPoints, speed, strength, intelligence, dexterity, mana, level);
        coordinates = new Point().randomPoint(1, 1, length-1, height-1);
    }

    public Player(int length, int height, String name){
        super(name, " ", new Attribute(20,20), new Attribute(10, 10), new Attribute(5,5), new Attribute(5,5), new Attribute(5,5), new Attribute(20,20), 1);
        coordinates = new Point().randomPoint(1, 1, length-1, height-1);
    }

    @Override
    public void move(String command){
        previousCommand = command;
        super.move(command);
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