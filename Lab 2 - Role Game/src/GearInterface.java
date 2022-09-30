public interface GearInterface {
    int getAttack();
    int getDefense();
    String getName();
    GearInterface combine(GearInterface other);
}
