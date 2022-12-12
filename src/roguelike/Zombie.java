package roguelike;

public class Zombie extends GameCharacter{
    public Zombie(int length, int height, int level) {
        super(length, height, "Zombie", "Rotten corpse, now alive again. Can't really think with its decayed brain though. Slow but strong.", new Attribute(25), new Attribute(5), new Attribute(20), new Attribute(0), new Attribute(0), new Attribute(0), level);
    }
}