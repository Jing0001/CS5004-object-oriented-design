public class Bird {
    //member variable
    private int id;
    private String name;
    private Food[] food;
    private int foodQuantity;
    private Type type;
    private String characteristic;
    private boolean extinct;
    private int wings;

    //constructor: create a bird object
    public Bird(int id, String name, Food[] food, int foodQuantity, Type type, String characteristic, boolean extinct, int wings) {
        this.id = id;
        this.name = name;
        this.food = food;
        this.foodQuantity = foodQuantity;
        this.type = type;
        this.characteristic = characteristic;
        this.extinct = extinct;
        this.wings = wings;
        if (wings < 0) {
            throw new IllegalArgumentException("The number of wings cannot be negative.");
        }
    }

    //getter for id
    public int getId() {
        return id;
    }

    //setter for id
    public int setId(int id) {
        this.id = id;
        return id;
    }

    //getter for name
    public String getName() {
        return name;
    }

    //setter for name
    public String setName(String name) {
        this.name = name;
        return name;
    }

    //getter for food
    public Food[] getFood() {
        return food;
    }

    //setter for food
    public Food[] setFood(Food[] food) {
        this.food = food;
        return food;
    }

    //getter for foodQuantity
    public int getFoodQuantity() {
        return foodQuantity;
    }

    //setter for foodQuantity
    public int setFoodQuantity(int foodQuantity) {
        this.foodQuantity = foodQuantity;
        return foodQuantity;
    }

    //getter for type
    public Type getType() {
        return type;
    }

    //setter for type
    public Type setType(Type type) {
        this.type = type;
        return type;
    }

    //getter for characteristic
    public String getCharacteristic() {
        return characteristic;
    }

    //setter for characteristic
    public String setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
        return characteristic;
    }

    //getter for extinct
    public boolean getExtinct() {
        return extinct;
    }

    //setter for extinct
    public boolean setExtinct(boolean extinct) {
        this.extinct = extinct;
        return extinct;
    }

    //getter for wings
    public int getWings() {
        return wings;
    }

    //setter for wings
    public int setWings(int wings) {
        this.wings = wings;
        return wings;
    }
}
