public class HeadGear extends Gear{
    //member variable
    private HeadGearType type;
    //constructor to initialize the variable
    public HeadGear(String adj, HeadGearType type, int attack, int defense) {
        super(adj, attack, defense);
        this.type = type;
        //attack exception
        if (attack < 0){
            throw new IllegalArgumentException("Attack strength cannot be negative");
        }
        //defense exception
        if (defense < 0){
            throw new IllegalArgumentException("Defense strength cannot be negative");
        }
    }

    @Override
    //getter method for name
    public String getName() {
        return super.getName() + " " + type;
    }
    //getter method for type
    public HeadGearType getType(){return type;}
    //combine two items of the same type
    public HeadGear combine(HeadGear other){
        String adj = other.getAdj() + " ";
        adj += this.getAdj();
        HeadGear newH = new HeadGear(adj, other.getType(),
                this.getAttack() + other.getAttack(), this.getDefense() + other.getDefense());
        return newH;
    }
}
