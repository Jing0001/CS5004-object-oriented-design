public class Battle {
    //member variable
    private Character player1;
    private Character player2;
    private Gear[] gearArray;

    //constructor to initialize the variable
    public Battle(Character player1, Character player2, Gear[] gearArray) {
        this.player1 = player1;
        this.player2 = player2;
        this.gearArray = gearArray;
    }
    //this method let two characters take turns to dress themself
    public void play(Character player1, Character player2, Gear[] gearArray) {
        int i = 0;
        //the gearArray has 10 possible choices, so one play has five rounds
        while (i < 5) {
            round(player1, player2, gearArray);
            i++;
        }
        System.out.println(output(player1, player2));
    }
    //this method represent each turn allows each character to choose one item,
    // remove that item from the following turns, and print out each character in
    // the fight along with what they are wearing and their attack and defense strength
    public void round(Character player1, Character player2, Gear[] gearArray) {
        player1.add(gearArray);
        System.out.println(player1.status());
        player2.add(gearArray);
        System.out.println(player2.status());
    }
    //this method calculate the damage and prints out who is the winner of the battle
    public String output(Character player1, Character player2){
        String result;
        //Damage is calculated by one's opponent's attack power minus that character's defense points.
        int damage1 = player2.getTotalAttack() - player1.getTotalDefense();
        int damage2 = player1.getTotalAttack() - player2.getTotalDefense();
        //The winner is determined by who has less damage after a battle
        if (damage1 > damage2) {
            result = "Player 2 wins";
        }
        else if (damage1 < damage2) {
            result = "Player 1 wins";
        }
        else {
            result = "There is a tie";
        }
        return result;
    }
    //the main driver
    public static void main(String[] args) {
        Character player1 = new Character("A", 0, 0);
        Character player2 = new Character("B", 0, 0);
        Gear[] gearArray = {
                new HeadGear("Good", HeadGearType.HATS, 20, 1),
                new HeadGear("Nice", HeadGearType.HELMETS, 11, 1),
                new HandGear("White", HandGearType.GLOVES, 33, 1),
                new HandGear("White", HandGearType.SWORDS, 1, 22),
                new HandGear("Black", HandGearType.GLOVES, 4, 1),
                new HandGear("Black", HandGearType.SHIELD, 1, 1),
                new FootWear("Happy", FootWearType.BOOTS,6, 9),
                new FootWear("Happy", FootWearType.SNEAKERS, 100, 1),
                new FootWear("Sad", FootWearType.BOOTS, 1, 33),
                new FootWear("Sad", FootWearType.SNEAKERS, 1, 55)
        };
        Battle a = new Battle(player1, player2, gearArray);
        a.round(player1, player2, gearArray);
        a.play(player1, player2, gearArray);
        a.output(player1, player2);
    }
}
