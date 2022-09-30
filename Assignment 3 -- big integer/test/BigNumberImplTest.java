import org.junit.Before;
import org.junit.Test;
import bignumber.BigNumber;
import bignumber.BigNumberImpl;

import static org.junit.Assert.assertEquals;

class BigNumberImplTest {
    //member variable
    private BigNumberImpl n;

    //setter
    @Before
    public void setUp(){
        n = new BigNumberImpl();
    }

    //test the length method
    @Test
    public void testLength(){
        assertEquals(1, n.length());
    }

    //test the ShiftLeft method
    @Test
    public void testShiftLeft(){
        n.shiftLeft(1);
        assertEquals(2, n.length());
    }
    //test the ShiftRight method
    @Test
    public void testShiftRight(){
        n.shiftRight(1);
        assertEquals(2, n.length());
    }

    //test the AddDigit method
    @Test
    public void testAddDigit(){
        n.addDigit(3);
        assertEquals(3, n.length());
    }

    //test the GetDigitAt method
    @Test
    public void testGetDigitAt(){
        assertEquals(0, n.getDigitAt(0));
    }

    //test the exception
    @Test(expected = IllegalArgumentException.class)
    public void testException(){
        n.getDigitAt(-1);
    }

    //test the copy method
    @Test
    public void testCopy(){
        assertEquals(n.length(), n.copy().length());
    }

    //test the addDigit method
    @Test
    public void testAddD(){
        n.addDigit(1);
        assertEquals(2, n.length());
    }

    //test the add method
    @Test
    public void testAdd(){
        n.add(new BigNumberImpl("1"));
        assertEquals(2, n.length());
    }

    //test the toString method
    @Test
    public void testToS(){
        n.addDigit(1);
        assertEquals("1", n.toString());
    }

    //test the compare method
    @Test
    public void testCompare(){
        BigNumber a = new BigNumberImpl("12");
        assertEquals(1, n.compareTo(a));
    }
}