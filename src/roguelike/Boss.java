package roguelike;

public class Boss extends Monster{
    public Boss(Point coordinates){
        this.coordinates = coordinates;
        this.name = "Boss";
        this.healthPoints = new Attribute(40);
        this.speed = 1.9;
        this.damage = 20;
        this.mana = new Attribute(100);
        this.level = 20;
        this.state = State.ALIVE;
    }

    public State getState(){
        if(state == State.DEAD){
            return State.BOSS_DEAD;
        }

        return state;
    }
}
