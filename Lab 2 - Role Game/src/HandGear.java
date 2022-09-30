public class HandGear extends Gear{
    private HandGearType type;

    public HandGear(String adj, HandGearType type, int attack, int defense) {
        super(adj, attack, defense);
        this.type = type;
    }

    @Override
    public String getName() {
        return super.getName() + " " + type;
    }
    public HandGearType getType(){return type;}

    public HandGear combine(HandGear other){
        String adj = other.getAdj() + " ";
        adj += this.getAdj();
        HandGear newH = new HandGear(adj, other.getType(),
                this.getAttack() + other.getAttack(), this.getDefense() + other.getDefense());
        return newH;
    }
}
