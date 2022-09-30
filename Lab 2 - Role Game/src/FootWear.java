public class FootWear extends Gear{
    private FootWearType type;

    public FootWear(String adj, FootWearType type, int attack, int defense) {
        super(adj, attack, defense);
        this.type = type;
    }

    @Override
    public String getName() {
        return super.getName() + " " + type;
    }
    public FootWearType getType(){return type;}
    public FootWear combine(FootWear other){
        String adj = other.getAdj() + " ";
        adj += this.getAdj();
        FootWear newH = new FootWear(adj, other.getType(),
                this.getAttack() + other.getAttack(), this.getDefense() + other.getDefense());
        return newH;
    }
}
