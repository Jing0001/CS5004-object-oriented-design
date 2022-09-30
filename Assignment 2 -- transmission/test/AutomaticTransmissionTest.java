import org.junit.Before;
import org.junit.Test;
import transmission.Transmission;
import transmission.AutomaticTransmission;

import static org.junit.Assert.*;

public class AutomaticTransmissionTest {
    //variable
    private AutomaticTransmission transmission;

    //create an AutomaticTransmission object
    @Before
    public void setUp() {
        transmission = new AutomaticTransmission(10, 20, 30, 40, 50);
    }

    //invalid speed
    @Test(expected = IllegalArgumentException.class)
    public void testException0() {
        transmission = new AutomaticTransmission(10, 20, 0, 40, 50);
    }

    //invalid speed after decrease
    @Test(expected = IllegalStateException.class)
    public void testException1() {
        transmission.decreaseSpeed();
    }

    //test increaseSpeed method
    @Test
    public void testIncreaseSpeed() {
        transmission.increaseSpeed();
        assertEquals(2, transmission.getSpeed());
        assertEquals(1, transmission.getGear());
    }

    //test decreaseSpeed method
    @Test
    public void testDecreaseSpeed() {
        transmission.increaseSpeed();
        transmission.decreaseSpeed();
        assertEquals(0, transmission.getSpeed());
        assertEquals(1, transmission.getGear());
    }

    //test toString method
    @Test
    public void testToString() {
        assertEquals("Transmission (speed = 0, gear = 0)", transmission.toString());
    }
}