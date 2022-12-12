package roguelike;

public class Skeleton extends GameCharacter{
    public Skeleton(int length, int height, int level) { //FIXME proper description and attributes
        super(length, height, "Vampire", "Ancient animated skeleton, lurking the graveyard. Constantly protecting tombstones motivated by its ancestral duty.", new Attribute(15,15), new Attribute(15,15), new Attribute(15,15), new Attribute(30,30), new Attribute(5,5), new Attribute(40,40), level);
    }
}
