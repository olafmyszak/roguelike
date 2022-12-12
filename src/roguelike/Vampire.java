package roguelike;

public class Vampire extends GameCharacter{
    public Vampire(int length, int height, int level) {
        super(length, height, "Vampire", "Powerful undead Count, feasting on the blood of humans. Faster and more intelligent than any normal human.", new Attribute(15), new Attribute(15), new Attribute(15), new Attribute(30), new Attribute(5), new Attribute(40), level);
    }
}