public abstract class WaterBird extends Bird implements NearWater{
    //member variable
    private WaterBody waterBody;

    //constructor: create an WaterBird object
    public WaterBird(int id, String name, Food[] food, int foodQuantity, Type type, String characteristic, boolean extinct, int wings, WaterBody waterBody) {
        super(id, name, food, foodQuantity, type, characteristic, extinct, wings);
        this.waterBody = waterBody;
    }

    public WaterBody getWaterBody() {
        return waterBody;
    }
}
