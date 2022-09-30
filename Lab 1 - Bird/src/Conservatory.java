import java.util.HashMap;
import java.util.TreeMap;

public class Conservatory {
    //member variable
    private Aviary[] conservatory;
    final static int NUM_AVIARY = 20;

    //constructor: create an array to represent 20 aviaries in the conservatory
    public Conservatory() {
        this.conservatory = new Aviary[NUM_AVIARY];
        for (int i = 0; i < NUM_AVIARY; i++) {
            conservatory[i] = new Aviary();
        }
    }

    //add bird and return the aviary index that bird is in
    public int addBird(Bird bird) {
        // check extinct
        if (bird.getExtinct()) {
            return -1;
        }
        // find an available aviary
        for (int i = 0; i < 20; i++) {
            if (conservatory[i].isFull()) {
                continue;
            }
            // check whether can mix
            if (conservatory[i].canMix(bird)) {
                // put it in the aviary
                conservatory[i].addBird(bird);
                return i;
            }
        }
        //no available aviary
        return -1;
    }

    //look up which aviary a bird is in
    public int checkAviary(int id) {
        for (int i = 0; i < 20; i++) {
            if (conservatory[i].isExist(id)) {
                return i;
            }
        }
        return -1;
    }

    //Print a sign for any given aviary such as description and information
    public void sign(int aviaryId) {
        conservatory[aviaryId].sign();
    }

    //Print a “map” that lists all the aviaries by location and the birds they house
    public String map() {
        String message = "";
        for (int i = 0; i < 20; i++) {
            if (!conservatory[i].isEmpty()) {
                System.out.println(i + ": " + conservatory[i].map());
                message += i + ": " + conservatory[i].map();
            }
        }
        return message;
    }

    //Print an index that lists all birds in the conservatory in alphabetical order and their location
    public String birdLocation() {
        String message = "";
        //create a HashMap object called allBirds
        HashMap<String, Integer> allBirds = new HashMap<>();
        //loop through all 20 aviaries in the conservatory
        for (int i = 0; i < 20; i++) {
            Aviary thisAviary = conservatory[i];
            Bird[] birds = thisAviary.getBirds();
            //check whether the aviary is empty
            for (int j = 0; j < thisAviary.size(); j++) {
                allBirds.put(birds[j].getName(), i);
            }
        }
        //create a treemap to store hashmap allBirds values
        TreeMap<String, Integer> sortedBirds = new TreeMap<>(allBirds);

        //display the treemap which is naturally sorted
        for (HashMap.Entry<String, Integer> entry : sortedBirds.entrySet()) {
            System.out.println("Name: " + entry.getKey() + ", Aviary No." + entry.getValue());
            message += "Name: " + entry.getKey() + ", Aviary No." + entry.getValue();
        }
        return message;
    }

    //Calculate what food needs to be kept and in what quantities for the conservatory
    public String foodQ() {
        String message = "";
        HashMap<Food, Integer> totalFood = new HashMap<Food, Integer>();
        for (int i = 0; i < 20; i++) {
            if (!conservatory[i].isEmpty()) {
                // get this aviary's hashmap
                HashMap n = conservatory[i].foodQ();
                //loop through each key in hashmap
                for (int j = 0; j < n.size(); j++) {
                    Food key = (Food) n.keySet().toArray()[j];
                    int individualQ = (int) n.get(key);
                    //cumulate the total food quantity for this specific food
                    totalFood.put(key, totalFood.getOrDefault(key, 0) + individualQ);
                    message += key + ": " + totalFood.get(key) + "\n";
                }
            }
        }
        return message;
    }
}