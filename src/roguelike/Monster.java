package roguelike;

public class Monster extends GameCharacter {
    public Monster(int length, int height) {
        coordinates = new Point().randomPoint(1,1, length-1, height-1);
    }


}
