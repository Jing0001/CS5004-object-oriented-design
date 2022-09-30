package lookandsay;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class LookAndSayIteratorTest {
    //variable
    private LookAndSayIterator lookAndSay;
    //create an LookAndSayIterator object
    @Before
    public void setUp(){
        lookAndSay = new LookAndSayIterator();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testException(){
        lookAndSay = new LookAndSayIterator(new BigInteger("-1"));
    }

    @Test
    public void testPrev(){
        lookAndSay = new LookAndSayIterator(new BigInteger("2123"));
        assertEquals("2123", lookAndSay.prev().toString());
        assertEquals("1133", lookAndSay.prev().toString());
    }

    @Test
    public void testHasPrev(){
        lookAndSay = new LookAndSayIterator(new BigInteger("23"));
        assertEquals(true, lookAndSay.hasPrevious());
    }


    @Test
    public void testNext(){
        lookAndSay = new LookAndSayIterator(new BigInteger("23"));
        assertEquals("23", lookAndSay.next().toString());
    }

    @Test
    public void testNext1(){
        lookAndSay = new LookAndSayIterator(new BigInteger("23"));
        assertEquals("23", lookAndSay.next().toString());
        assertEquals("1213", lookAndSay.next().toString());
        assertEquals("11121113", lookAndSay.next().toString());
        lookAndSay.prev();
        assertEquals("11121113", lookAndSay.prev().toString());
        assertEquals("1213", lookAndSay.prev().toString());
        assertEquals("23", lookAndSay.prev().toString());
    }

    @Test
    public void testNext2(){
        lookAndSay = new LookAndSayIterator(new BigInteger("123"));
        assertEquals("123", lookAndSay.next().toString());
        assertEquals("111213", lookAndSay.next().toString());
        lookAndSay.prev();
        assertEquals("111213", lookAndSay.prev().toString());
        assertEquals("123", lookAndSay.prev().toString());
    }


    @Test
    public void testHasNext(){
        lookAndSay = new LookAndSayIterator(new BigInteger("23"));
        assertEquals(true, lookAndSay.hasNext());
    }
}