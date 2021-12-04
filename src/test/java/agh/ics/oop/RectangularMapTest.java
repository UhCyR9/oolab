package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class RectangularMapTest {
    AbstractWorldMap map = new RectangularMap(5, 5);
    Animal animal1 = new Animal(map, new Vector2d(1, 1));

    @Test
    void placeTest()
    {
        assertEquals(map, animal1.getMap());
        try {
            Animal animal2 = new Animal(map,new Vector2d(1,1));
        }
        catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
    }

    @Test
    void canMoveToTest()
    {
        assertFalse(map.canMoveTo(new Vector2d(1, 1)));
        assertTrue(map.canMoveTo(new Vector2d(1, 2)));
        assertFalse(map.canMoveTo(new Vector2d(5, 7)));
    }

    @Test
    void isOccupied()
    {
        assertTrue(map.isOccupied(new Vector2d(1,1)));
        assertFalse(map.isOccupied(new Vector2d(2,2)));
    }

    @Test
    void objectAtTest()
    {
        assertEquals(animal1,map.objectAt(new Vector2d(1,1)));
    }

}
