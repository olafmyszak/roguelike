package roguelike;

public abstract class GameCharacter {
    protected Point coordinates;
    protected String name;
    protected String description;
    protected Attribute healthPoints;
    protected Attribute speed;
    protected Attribute strength;
    protected Attribute intelligence;
    protected Attribute dexterity;
    protected Attribute mana;
    protected int level;

    protected void move(String command) {
        command = command.toLowerCase();
        switch (command){
            case "w" -> coordinates.setX(coordinates.getX()-1);
            case "s" -> coordinates.setX(coordinates.getX()+1);
            case "a" -> coordinates.setY(coordinates.getY()-1);
            case "d" -> coordinates.setY(coordinates.getY()+1);
        }
    }

    public Point getCoordinates(){
        return coordinates;
    }

    public int getX(){
        return coordinates.getX();
    }

    public int getY(){
        return coordinates.getY();
    }

    public GameCharacter(String name, String description, Attribute healthPoints, Attribute speed, Attribute strength, Attribute intelligence, Attribute dexterity, Attribute mana, int level) {
        this.name = name;
        this.description = description;
        this.healthPoints = healthPoints;
        this.speed = speed;
        this.strength = strength;
        this.intelligence = intelligence;
        this.dexterity = dexterity;
        this.mana = mana;
        this.level = level;
    }

    public GameCharacter()
    {

    }

    public void setCoordinates(int x, int y) {
        coordinates.setX(x);
        coordinates.setY(y);
    }
}