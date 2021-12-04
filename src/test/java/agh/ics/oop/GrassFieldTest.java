package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;


public class GrassFieldTest {
    GrassField map = new GrassField(10);
    Animal animal1 = new Animal(map, new Vector2d(1, 1));

    @Test
    void grassTest()
    {
        Map<Vector2d,IMapElement> passable = map.getPassable();
        assertEquals(10,passable.size());

        int maxXY = (int)sqrt(10*10)+1;
        for (Vector2d position : passable.keySet())
        {
            assertTrue(position.follows(new Vector2d(0,0)) && position.precedes(new Vector2d(maxXY,maxXY)));
        }
    }


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
        assertFalse(map.canMoveTo(new Vector2d(-1, 0)));
        assertTrue(map.canMoveTo(new Vector2d(1, 2)));
    }

    @Test
    void isOccupied()
    {
        assertTrue(map.isOccupied(new Vector2d(1,1)));
        System.out.println(map);
        animal1.move(MoveDirection.FORWARD);
        animal1.move(MoveDirection.FORWARD);
        System.out.println(map);
        assertTrue(map.isOccupied(new Vector2d(1,3)));
    }

    @Test
    void objectAtTest()
    {
        assertEquals(animal1,map.objectAt(new Vector2d(1,1)));
    }

}
