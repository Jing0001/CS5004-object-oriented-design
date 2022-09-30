import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class characterTest {
    //member variable
    private Character character;

    @Before
    //create a Battle object
    public void setUp(){
        character = new Character("A", 0, 0);
    }

    @Test
    //test the getter method for name
    public void testGetName(){
        assertEquals("A", character.getName());
    }

    @Test
    //test the getter method for head
    public void testGetHead(){
        assertEquals(null, character.getHead()[0]);
    }
    @Test
    //test the getter method for head
    public void testGetHead1(){
        character.head[0] = new HeadGear("Nice", HeadGearType.HELMETS, 11, 1);
        assertEquals("Nice HELMETS", character.getHead()[0].getName());
    }
    @Test
    //test the getter method for hand
    public void testGetHand(){
        assertEquals(null, character.getHand()[0]);
    }
    @Test
    //test the getter method for foot
    public void testGetFoot(){
        assertEquals(null, character.getFoot()[0]);
    }
    @Test
    //test the choose method, all available, pick the max attach strength
    public void testChoose(){
        Gear[] gearArray = {
                new HeadGear("Good", HeadGearType.HATS, 20, 1),
                new HeadGear("Nice", HeadGearType.HELMETS, 11, 1),
                new HandGear("White", HandGearType.GLOVES, 33, 1),
                new HandGear("White", HandGearType.SWORDS, 1, 1),
                new HandGear("Black", HandGearType.GLOVES, 1, 1),
                new HandGear("Black", HandGearType.SHIELD, 1, 1),
                new FootWear("Happy", FootWearType.BOOTS,1, 1),
                new FootWear("Happy", FootWearType.SNEAKERS, 1, 1),
                new FootWear("Sad", FootWearType.BOOTS, 1, 1),
                new FootWear("Sad", FootWearType.SNEAKERS, 1, 1)
        };
        Gear gear = new HandGear("White", HandGearType.GLOVES, 33, 1);
        assertEquals(gear.getName(), character.choose(gearArray).getName());
    }
    @Test
    //test the choose method, only foot available, pick the max defense within max attach strength items
    public void testChoose1(){
        character.head[0] = new HeadGear("Nice", HeadGearType.HELMETS, 11, 1);
        character.hand[0] = new HandGear("Black", HandGearType.GLOVES, 1, 1);
        character.hand[1] = new HandGear("White", HandGearType.GLOVES, 1, 1);
        character.foot[0] = new FootWear("Happy", FootWearType.SNEAKERS, 1, 1);
        Gear[] gearArray = {
                new HeadGear("Good", HeadGearType.HATS, 10, 1),
                new HeadGear("Nice", HeadGearType.HELMETS, 11, 1),
                new HandGear("White", HandGearType.GLOVES, 1, 1),
                new HandGear("White", HandGearType.SWORDS, 1, 1),
                new HandGear("Black", HandGearType.GLOVES, 1, 1),
                new HandGear("Black", HandGearType.SHIELD, 1, 1),
                new FootWear("Happy", FootWearType.BOOTS,33, 1),
                new FootWear("Happy", FootWearType.SNEAKERS, 33, 1),
                new FootWear("Sad", FootWearType.BOOTS, 33, 10),
                new FootWear("Sad", FootWearType.SNEAKERS, 1, 1)
        };
        Gear gear = new FootWear("Sad", FootWearType.BOOTS, 33, 10);
        assertEquals(gear.getName(), character.choose(gearArray).getName());
    }
    @Test
    //test the add method
    public void testAdd(){
        Gear[] gearArray = {
                new HeadGear("Good", HeadGearType.HATS, 10, 1),
                new HeadGear("Nice", HeadGearType.HELMETS, 11, 1),
                new HandGear("White", HandGearType.GLOVES, 1, 1),
                new HandGear("White", HandGearType.SWORDS, 1, 1),
                new HandGear("Black", HandGearType.GLOVES, 1, 1),
                new HandGear("Black", HandGearType.SHIELD, 1, 1),
                new FootWear("Happy", FootWearType.BOOTS,1, 1),
                new FootWear("Happy", FootWearType.SNEAKERS, 1, 1),
                new FootWear("Sad", FootWearType.BOOTS, 1, 1),
                new FootWear("Sad", FootWearType.SNEAKERS, 1, 1)
        };
        character.add(gearArray);
        assertEquals("Nice HELMETS", character.getHead()[0].getName());
    }
    @Test
    //test the status method
    public void testStatus(){
        character.head[0] = new HeadGear("Nice", HeadGearType.HELMETS, 11, 1);
        assertEquals("A: Nice HELMETS(attack:11,defense:1)", character.status());
    }
    @Test
    //test the status method
    public void testStatus1(){
        character.head[0] = new HeadGear("Nice", HeadGearType.HELMETS, 11, 1);
        character.hand[0] = new HandGear("Black", HandGearType.GLOVES, 1, 1);
        assertEquals("A: Nice HELMETS(attack:11,defense:1)Black GLOVES(attack:1,defense:1)", character.status());
    }
    @Test
    //test the TotalAttack method
    public void testTotalAttack(){
        character.head[0] = new HeadGear("Nice", HeadGearType.HELMETS, 11, 1);
        character.hand[0] = new HandGear("Black", HandGearType.GLOVES, 1, 1);
        assertEquals(12, character.getTotalAttack());
    }
    @Test
    //test the TotalDefense method
    public void testTotalDefense(){
        character.head[0] = new HeadGear("Nice", HeadGearType.HELMETS, 11, 1);
        character.hand[0] = new HandGear("Black", HandGearType.GLOVES, 1, 1);
        assertEquals(2, character.getTotalDefense());
    }
}