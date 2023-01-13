package roguelike;

public class Ghost extends GameCharacter{
    public Ghost(int length, int height, int level) {
        super(length, height, "Ghost", "Restless soul, stuck in the mortal realm. Cannot be harmed by standard attacks and weapons.", new Attribute(1), new Attribute(15), new Attribute(15), new Attribute(30), new Attribute(5), new Attribute(40), level);
    }
}
