import java.util.HashMap;

public class Aviary {
    //member variable
    private Bird[] birds;
    private int size;
    final static int MAX_AVIARY = 5;

    //constructor: create an array to represent 5 birds in the aviary
    public Aviary() {
        this.birds = new Bird[MAX_AVIARY];
        size = 0;
    }

    // getter for size
    public int size() {
        return size;
    }

    //getter for Birds
    public Bird[] getBirds() {
        return birds;
    }

    //check whether this aviary is full
    public boolean isFull() {
        if (this.size() == 5) {
            return true;
        }
        return false;
    }

    //check whether this aviary is empty
    public boolean isEmpty() {
        if (this.size() == 0) {
            return true;
        }
        return false;
    }

    //check whether the given bird exist in this aviary
    public boolean isExist(int id) {
        for (int i = 0; i < size; i++) {
            if (birds[i].getId() == id) {
                return true;
            }
        }
        return false;
    }

    //add bird to aviary
    public boolean addBird(Bird bird) {
        for (int i = 0; i < 5; i++) {
            if (birds[i] == null) {
                birds[i] = bird;
                size++;
                return true;
            }
        }
        return false;
    }

    //Flightless birds, birds of prey, and waterfowl should not be mixed with other bird types
    public boolean canMix(Bird bird) {
        //FLIGHTLESS_BIRDS
        if (bird.getType() == Type.EMUS || bird.getType() == Type.KIWIS || bird.getType() == Type.MOAS) {
            for (int i = 0; i < this.size(); i++) {
                if (birds[i].getType() == Type.EMUS
                        || birds[i].getType() == Type.KIWIS
                        || birds[i].getType() == Type.MOAS) {
                    return false;
                }
                return true;
            }
        }
        //Birds of PREY
        else if (bird.getType() == Type.HAWKS || bird.getType() == Type.EAGLES || bird.getType() == Type.OSPREY) {
            for (int i = 0; i < this.size(); i++) {
                if (birds[i].getType() == Type.HAWKS
                        || birds[i].getType() == Type.EAGLES
                        || birds[i].getType() == Type.OSPREY) {
                    return false;
                }
                return true;
            }
        }
        //WATERFOWL
        else if (bird.getType() == Type.DUCKS || bird.getType() == Type.SWANS || bird.getType() == Type.GEESE) {
            for (int i = 0; i < this.size(); i++) {
                if (birds[i].getType() == Type.DUCKS
                        || birds[i].getType() == Type.SWANS
                        || birds[i].getType() == Type.GEESE) {
                    return true;
                }
                return false;
            }
        } else {
            for (int i = 0; i < this.size(); i++) {
                if (birds[i].getType() == Type.DUCKS
                        || birds[i].getType() == Type.SWANS
                        || birds[i].getType() == Type.GEESE
                        || birds[i].getType() == Type.EMUS
                        || birds[i].getType() == Type.KIWIS
                        || birds[i].getType() == Type.MOAS
                        || birds[i].getType() == Type.HAWKS
                        || birds[i].getType() == Type.EAGLES
                        || birds[i].getType() == Type.OSPREY) {
                    return false;
                }
                return true;
            }
        }
        return true;
    }

    //Calculate what food needs to be kept and in what quantities for this aviary
    public HashMap<Food, Integer> foodQ() {
        HashMap<Food, Integer> foodHashMap = new HashMap<>();
        for (int i = 0; i < this.size(); i++) {
            Food[] foods = birds[i].getFood();
            int individualQuantity = birds[i].getFoodQuantity();
            for (int j = 0; j < foods.length; j++) {
                foodHashMap.put(foods[j], foodHashMap.getOrDefault(foods[j], 0) + individualQuantity);
            }
        }
        return foodHashMap;
    }

    //Print a sign for any given aviary such as description and information
    public String sign() {
        String message = "";
        for (int i = 0; i < this.size(); i++) {
            System.out.println(birds[i].getType() + ": " + birds[i].getCharacteristic());
            message += birds[i].getType() + ": " + birds[i].getCharacteristic();
        }
        return message;
    }

    //Print a “map” that lists all the aviaries by location and the birds they house
    public String map() {
        String message = "";
        for (int i = 0; i < this.size(); i++) {
            System.out.println(birds[i].getType());
            message += birds[i].getType();
        }
        return message;
    }
}