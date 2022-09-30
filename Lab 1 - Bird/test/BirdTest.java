import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

//A JUnit test class for the Bird class.
public class BirdTest {
    private Bird myParrot;

    //set up a myParrot variable
    @Before
    public void setUp() {
        Food[] food = new Food[]{Food.SEEDS, Food.VEGETATION};
        myParrot = new Bird(001, "a1", food, 500, Type.GRAY_PARROT, "friendly", false, 2);
    }

    //test the getId method
    @Test
    public void testGetId(){
        assertEquals(001, myParrot.getId());
    }

    //test the setId method
    @Test
    public void testSetId(){
        assertEquals(002, myParrot.setId(002));
    }

    //test the getId method
    @Test
    public void testGetName(){
        assertEquals("a1", myParrot.getName());
    }

    //test the setId method
    @Test
    public void testSetName(){
        assertEquals("a2", myParrot.setName("a2"));
    }

    //test the getFood method
    @Test
    public void testGetFood(){
        Food[] expected = new Food[]{Food.SEEDS, Food.VEGETATION};
        assertEquals(expected, myParrot.getFood());
    }

    //test the setFood method
    @Test
    public void testSetFood(){
        Food[] expected = new Food[]{Food.NUTS, Food.SEEDS, Food.VEGETATION};
        assertEquals(expected, myParrot.setFood(new Food[]{Food.NUTS, Food.SEEDS, Food.VEGETATION}));
    }

    //test the getFoodQuantity method
    @Test
    public void testGetFoodQuantity(){
        assertEquals(500, myParrot.getFoodQuantity());
    }

    //test the setFoodQuantity method
    @Test
    public void testSetFoodQuantity(){
        assertEquals(200, myParrot.setFoodQuantity(200));
    }

    //test the getType method
    @Test
    public void testGetType(){
        assertEquals(Type.GRAY_PARROT, myParrot.getType());
    }

    //test the setType method
    @Test
    public void testSetType(){
        assertEquals(Type.DUCKS, myParrot.setType(Type.DUCKS));
    }

    //test the getCharacteristic method
    @Test
    public void testGetCharacteristic(){
        assertEquals("friendly", myParrot.getCharacteristic());
    }

    //test the setType method
    @Test
    public void testSetCharacteristic(){
        assertEquals("multi-coloured", myParrot.setCharacteristic("multi-coloured"));
    }

    //test the getExtinct method
    @Test
    public void testGetExtinct(){
        assertEquals(false, myParrot.getExtinct());
    }

    //test the setExtinct method
    @Test
    public void testSetExtinct(){
        assertEquals(true, myParrot.setExtinct(true));
    }

    //test the getWings method
    @Test
    public void testGetWings(){
        assertEquals(2, myParrot.getWings());
    }

    //test the setWings method
    @Test
    public void testSetWings(){
        assertEquals(4, myParrot.setWings(4));
    }

    //test the invalid number of wings
    @Test(expected = IllegalArgumentException.class)
    public void testExceptionWings() {
        Food[] food = new Food[]{Food.SEEDS, Food.VEGETATION};
        myParrot = new Bird(001, "a1", food, 500, Type.GRAY_PARROT, "friendly", false, -2);
    }
}