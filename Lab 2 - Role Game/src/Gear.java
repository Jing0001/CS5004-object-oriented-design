public abstract class Gear implements GearInterface{
    private int attack;
    private int defense;
    private String adj;
    //private Type type;
    public Gear(String adj) {
        this.adj = adj;
        //this.type = type;
    }

    public Gear(String adj, int attack, int defense) {
        this.adj = adj;
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    public int getAttack() {

        return attack;
    }

    @Override
    public int getDefense() {

        return defense;
    }
    public String getAdj(){
        return adj;
    }
    @Override
    public String getName() {
        return adj + "";
    }

    @Override
    public GearInterface combine(GearInterface other) {
        return null;
    }


}
