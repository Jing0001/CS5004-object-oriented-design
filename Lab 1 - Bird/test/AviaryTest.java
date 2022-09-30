import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

//A JUnit test class for the Aviary class.
public class AviaryTest {
    private Aviary myAviary;

    //set up a myAviary variable
    @Before
    public void setUp() {
        myAviary = new Aviary();
        Bird newBird = new Bird(02, "Alice", new Food[]{Food.NUTS, Food.SEEDS}, 50, Type.DUCKS, "friendly", false, 2);
        myAviary.addBird(newBird);
    }

    //test the size method
    @Test
    public void testSize() {
        assertEquals(1, myAviary.size());
    }

    //test the GetBirds method
    @Test
    public void testGetBirds() {
        assertEquals(5, myAviary.getBirds().length);
    }

    //test the isFull method
    @Test
    public void testIsFull() {
        assertEquals(false, myAviary.isFull());
    }

    //test the IsEmpty method
    @Test
    public void testIsEmpty() {
        assertEquals(false, myAviary.isEmpty());
    }

    //test the AddBird method
    @Test
    public void testAddBird() {
        Bird newBird = new Bird(11, "AB", new Food[]{Food.VEGETATION, Food.SEEDS}, 50, Type.GRAY_PARROT, "not friendly", false, 2);
        assertEquals(true, myAviary.addBird(newBird));
    }

    //take a same type bird and test the canMix method
    @Test
    public void testCanMixSameType() {
        Bird newBird = new Bird(01, "Abby", new Food[]{Food.VEGETATION, Food.SEEDS}, 50, Type.SWANS, "friendly", false, 2);
        assertEquals(true, myAviary.canMix(newBird));
    }

    //take a different type bird that cannot mix and test the canMix method
    @Test
    public void testCanMixDiffType() {
        Bird newBird = new Bird(01, "Abby", new Food[]{Food.VEGETATION, Food.SEEDS}, 50, Type.PIGEONS, "friendly", false, 2);
        assertEquals(false, myAviary.canMix(newBird));
    }

    //test the sign method
    @Test
    public void testSign() {
        String expected = "DUCKS: friendly";
        assertEquals(expected, myAviary.sign());
    }

    //test map method
    @Test
    public void testMap() {
        String expected = "DUCKS";
        assertEquals(expected, myAviary.map());
    }

    //test the FoodQ method
    @Test
    public void testFoodQ() {
        HashMap<Food, Integer> expected = new HashMap<>();
        expected.put(Food.NUTS, 50);
        expected.put(Food.SEEDS, 50);
        assertEquals(expected, myAviary.foodQ());
    }
}