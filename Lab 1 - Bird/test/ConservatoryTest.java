import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

//A JUnit test class for the Conservatory class.
public class ConservatoryTest {
    private Conservatory myConservatory;

    //set up a Conservatory object
    @Before
    public void setUp() {
        myConservatory = new Conservatory();
        Bird newBird = new Bird(01, "Alice", new Food[]{Food.NUTS, Food.SEEDS}, 50, Type.PIGEONS, "friendly", false, 2);
        myConservatory.addBird(newBird);

    }

    //test that extinct birds cannot be added to an aviary
    @Test
    public void testAddExtinctBird() {
        Bird newBird = new Bird(01, "Abby", new Food[]{Food.VEGETATION, Food.SEEDS}, 50, Type.GRAY_PARROT, "friendly", true, 2);
        assertEquals(-1, myConservatory.addBird(newBird));
    }

    //test that no extinct birds can be added to an aviary
    @Test
    public void testAddBird() {
        Bird newBird = new Bird(01, "Abby", new Food[]{Food.VEGETATION, Food.SEEDS}, 50, Type.GRAY_PARROT, "friendly", false, 2);
        assertEquals(0, myConservatory.addBird(newBird));
    }

    //test the check aviary method
    @Test
    public void testCheckAviary() {
        assertEquals(0, myConservatory.checkAviary(01));
    }

    //test the map method
    @Test
    public void testMap() {
        assertEquals("0: PIGEONS", myConservatory.map());
    }

    //test the birdLocation method
    @Test
    public void testBirdLocation() {
        assertEquals("Name: Alice, Aviary No.0", myConservatory.birdLocation());
    }

    //test the FoodQ method
    @Test
    public void testFoodQ() {
        assertEquals("SEEDS: 50\nNUTS: 50\n", myConservatory.foodQ());
    }
}