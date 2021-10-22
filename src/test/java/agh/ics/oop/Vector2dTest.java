package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {

    @Test
    void testEquals()
    {
        Vector2d o = new Vector2d(2,1);
        assertTrue(o.equals(o));
        assertTrue(new Vector2d(2,1).equals(new Vector2d(2,1)));
        assertFalse(new Vector2d(1,2).equals(new Vector2d(2,1)));
        assertFalse(new Vector2d(1,2).equals(5));


    }

    @Test
    void testToString()
    {
        assertEquals("(1,2)", new Vector2d(1,2).toString());
        assertEquals("(-1,-2)", new Vector2d(-1,-2).toString());
    }

    @Test
    void testPrecedes()
    {
        assertTrue(new Vector2d(1,1).precedes(new Vector2d(2,2)));
        assertFalse(new Vector2d(1,2).precedes(new Vector2d(2,1)));
    }

    @Test
    void testFollows()
    {
        assertTrue(new Vector2d(2,2).follows(new Vector2d(1,1)));
        assertFalse(new Vector2d(1,2).follows(new Vector2d(2,1)));
    }

    @Test
    void testUpperRight()
    {
        assertEquals(new Vector2d(1,1), new Vector2d(1,0).upperRight(new Vector2d(0,1)) );
    }

    @Test
    void testLowerLeft()
    {
        assertEquals(new Vector2d(0,0), new Vector2d(1,0).lowerLeft(new Vector2d(0,1)) );
    }

    @Test
    void testAdd()
    {
        assertEquals(new Vector2d(5,1), new Vector2d(2,0).add(new Vector2d(3,1)));
        assertEquals(new Vector2d(-2,-2), new Vector2d(3,1).add(new Vector2d(-5,-3)));
    }

    @Test
    void testSubtract()
    {
        assertEquals(new Vector2d(3,3), new Vector2d(5,4).subtract(new Vector2d(2,1)));
        assertEquals(new Vector2d(-1,-2), new Vector2d(1,3).subtract(new Vector2d(2,5)));
    }

    @Test
    void testOpposite()
    {
        assertEquals(new Vector2d(2,-3), new Vector2d(-2,3).opposite());
        assertEquals(new Vector2d(0,0), new Vector2d(0,0));
    }
}
