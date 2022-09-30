import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeadGearTest {
    //member variable
    private HeadGear headGear;
    @Before
    //create a Battle object
    public void setUp(){
        headGear = new HeadGear("good", HeadGearType.HATS, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionAttack(){
        HeadGear headGear = new HeadGear("white", HeadGearType.HATS, -1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionDefense(){
        HeadGear headGear = new HeadGear("white", HeadGearType.HATS, 1, -4);
    }
    @Test
    public void testGetName(){
        assertEquals("good HATS", headGear.getName());
    }

    @Test
    public void testGetType(){
        assertEquals(HeadGearType.HATS, headGear.getType());
    }
    @Test
    public void testCombine(){
        assertEquals("white good HELMETS", headGear.combine(new HeadGear("white", HeadGearType.HELMETS, 0, 0)).getName());
    }
}