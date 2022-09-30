public class Character {
    //member variable
    String name;
    final int attackBase;
    final int defenseBase;
    HeadGear[] head = new HeadGear[1];
    HandGear[] hand= new HandGear[2];
    FootWear[] foot = new FootWear[2];
    //constructor to initialize the variable
    public Character(String name, int attackBase, int defenseBase) {
        this.name = name;
        this.attackBase = attackBase;
        this.defenseBase = defenseBase;
    }
    //getter method for name
    public String getName(){
        return name;
    }
    //getter method for head
    public HeadGear[] getHead(){
        return head;
    }
    //getter method for hand
    public HandGear[] getHand(){
        return hand;
    }
    //getter method for foot
    public FootWear[] getFoot(){
        return foot;
    }
    //this method allows character to choose the item according to the rules
    public Gear choose(Gear[] gearArray) {
        //Rule1: Prefer the type of item that the character has available slot for.
        //so first check whether its available
        boolean headAvailable = head[0] == null;
        boolean handAvailable = hand[0] == null || hand[1] == null;
        boolean footAvailable = foot[0] == null || foot[1] == null;
        //if all unavailable, use combine and pick highest attack strength
        if (!headAvailable && !handAvailable && !footAvailable) {
            headAvailable = handAvailable = footAvailable = true;
        }
        //max attack strength gear among the gearArray that can pick
        Gear max = null;
        //skip the null slots of the gearArray
        for (int i = 0; i < 10; i++) {
            if (gearArray[i] == null) {
                continue;
            }
            //find available slot and max attack strength
            if (gearArray[i] instanceof HeadGear && headAvailable) {
                Gear cur = gearArray[i];
                if (max == null || cur.getAttack() > max.getAttack()) {
                    max = cur;
                }
            }
            if (gearArray[i] instanceof HandGear && handAvailable) {
                Gear cur = gearArray[i];
                if (max == null || cur.getAttack() > max.getAttack()) {
                    max = cur;
                }
            }
            if (gearArray[i] instanceof FootWear && footAvailable) {
                Gear cur = gearArray[i];
                if (max == null || cur.getAttack() > max.getAttack()) {
                    max = cur;
                }
            }
        }
        Gear[] candidates = new Gear[10];
        //check whether it is multiple choice
        int count = 0;
        for (int j = 0; j < 10; j++) {
            //1. it's an available gear(not null) 2. this gear's attack strength = max attack strength 3. can pick
            if (gearArray[j] != null
                    && gearArray[j].getAttack() == max.getAttack()
                    && (gearArray[j] instanceof HeadGear && headAvailable || gearArray[j] instanceof HandGear && handAvailable || gearArray[j] instanceof FootWear && footAvailable)) {
                candidates[count] = gearArray[j];
                count++;
            }
        }
        //only one item has the highest attack strength
        if (count == 1) {
            return max;
        }
        //find max defense strength among the max attack strength array
        Gear maxD = candidates[0];
        for (int k = 1; k < count; k++) {
            if (candidates[k].getDefense() > maxD.getDefense()) {
                maxD = candidates[k];
            }
        }
        //still a tie, pick a random one
        return maxD;
    }

    //choose the item from gearArray and remove that item from the gearArray
    public void add(Gear[] gearArray){
        Gear item = choose(gearArray);
        //remove that item from gearArray
        for (int a = 0; a < 10; a++){
            if (gearArray[a] == item){
                gearArray[a] = null;
                break;
            }
        }
        if (item instanceof HeadGear){
            if (getHead()[0] == null){
                getHead()[0] = (HeadGear) item;
            }else{
                head[0].combine((HeadGear) item);
            }
        }
        else if (item instanceof HandGear){
            if (hand[0] == null){
                hand[0] = (HandGear) item;
            }else if (hand[1] == null){
                hand[1] = (HandGear) item;
            }else{
                hand[1].combine((HandGear) item);
            }
        }
        else if (item instanceof FootWear){
            if (foot[0] == null){
                foot[0] = (FootWear) item;
            }else if (foot[1] == null){
                foot[1] = (FootWear) item;
            }else{
                foot[1].combine((FootWear) item);
            }

        }
    }
    // return the string represent each character in the fight
    // along with what they are wearing and their attack and defense strength
    public String status() {
        String message = "";
        if (getHead()[0] != null) {
            message += getHead()[0].getName() + "(attack:" + getHead()[0].getAttack() + ",defense:" + getHead()[0].getDefense() + ")";
        }
        if (getHand()[0] != null) {
            message += getHand()[0].getName() + "(attack:" + getHand()[0].getAttack() + ",defense:" + getHand()[0].getDefense() + ")";
        }
        if (getHand()[1] != null) {
            message += getHand()[1].getName() + "(attack:" + getHand()[1].getAttack() + ",defense:" + getHand()[1].getDefense() + ")";
        }
        if (getFoot()[0] != null) {
            message += getFoot()[0].getName() + "(attack:" + getFoot()[0].getAttack() + ",defense:" + getFoot()[0].getDefense() + ")";
        }
        if (getFoot()[1] != null) {
            message += getFoot()[1].getName() + "(attack:" + getFoot()[1].getAttack() + ",defense:" + getFoot()[1].getDefense() + ")";
        }
        return getName() + ": " + message;
    }

    //calculate the total attack of this character
    public int getTotalAttack(){
        int total = attackBase;
        if (getHead()[0] != null) {
            total += getHead()[0].getAttack();
        }
        if (getHand()[0] != null) {
            total += getHand()[0].getAttack();
        }
        if (getHand()[1] != null) {
            total += getHand()[1].getAttack();
        }
        if (getFoot()[0] != null) {
            total += getFoot()[0].getAttack();
        }
        if (getFoot()[1] != null) {
            total += getFoot()[1].getAttack();
        }
        return total;
    }
    //calculate the total defense of this character
    public int getTotalDefense(){
        int total = defenseBase;
        if (getHead()[0] != null) {
            total += getHead()[0].getDefense();
        }
        if (getHand()[0] != null) {
            total += getHand()[0].getDefense();
        }
        if (getHand()[1] != null) {
            total += getHand()[1].getDefense();
        }
        if (getFoot()[0] != null) {
            total += getFoot()[0].getDefense();
        }
        if (getFoot()[1] != null) {
            total += getFoot()[1].getDefense();
        }
        return total;
    }
}