import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class BattleTest {
    //member variable
    private Battle battle;
    //create a Battle object
    @Before
    public void setUp() {
        Character player1 = new Character("A", 0, 0);
        Character player2 = new Character("B", 0, 0);
        Gear[] gearArray = {
                new HeadGear("Good", HeadGearType.HATS, 1, 1),
                new HeadGear("Nice", HeadGearType.HELMETS, 1, 1),
                new HandGear("White", HandGearType.GLOVES, 1, 1),
                new HandGear("White", HandGearType.SWORDS, 1, 1),
                new HandGear("Black", HandGearType.GLOVES, 1, 1),
                new HandGear("Black", HandGearType.SHIELD, 1, 1),
                new FootWear("Happy", FootWearType.BOOTS,1, 1),
                new FootWear("Happy", FootWearType.SNEAKERS, 1, 1),
                new FootWear("Sad", FootWearType.BOOTS, 1, 1),
                new FootWear("Sad", FootWearType.SNEAKERS, 1, 1)
        };
        battle = new Battle(player1, player2, gearArray);
    }

    //test the output() method which there is a tie
    @Test
    public void testOutput(){
        assertEquals("There is a tie",
                battle.output(new Character("A", 0, 0),
                        new Character("B", 0, 0)));
    }
    //test the output() method
    @Test
    public void testOutput1(){
        Character player1 = new Character("A", 0, 0);
        player1.head[0] = new HeadGear("Good", HeadGearType.HATS, 8, 6);
        Character player2 = new Character("B", 0, 0);
        player2.foot[0] = new FootWear("Sad", FootWearType.SNEAKERS, 4, 7);
        assertEquals("Player 1 wins", battle.output(player1, player2));
    }
    //test the output() method
    @Test
    public void testOutput2(){
        Character player2 = new Character("A", 0, 0);
        player2.head[0] = new HeadGear("Good", HeadGearType.HATS, 8, 6);
        Character player1 = new Character("B", 0, 0);
        player1.foot[0] = new FootWear("Sad", FootWearType.SNEAKERS, 4, 7);
        assertEquals("Player 2 wins", battle.output(player1, player2));
    }
}