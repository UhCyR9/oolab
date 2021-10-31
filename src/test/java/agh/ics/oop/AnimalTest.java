package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {

    @Test
    void moveTest() {
        Animal test = new Animal();
        assertEquals(new Vector2d(2,2),test.getPosition());
        assertEquals(MapDirection.NORTH,test.getOrientation());

        // Czy wychodzi poza mapę
        test.move(MoveDirection.FORWARD);
        test.move(MoveDirection.FORWARD);
        test.move(MoveDirection.FORWARD);

        assertEquals(new Vector2d(2,4),test.getPosition());
        assertEquals(MapDirection.NORTH,test.getOrientation());

        //orientacja  i położenie
        test.move(MoveDirection.LEFT);

        assertEquals(new Vector2d(2,4),test.getPosition());
        assertEquals(MapDirection.WEST,test.getOrientation());

        test.move(MoveDirection.BACKWARD);
        test.move(MoveDirection.BACKWARD);
        test.move(MoveDirection.LEFT);

        assertEquals(new Vector2d(4,4),test.getPosition());
        assertEquals(MapDirection.SOUTH,test.getOrientation());

        for (int i = 0; i<6; i++)
        {
            test.move(MoveDirection.FORWARD);
        }

        test.move(MoveDirection.LEFT);

        assertEquals(new Vector2d(4,0),test.getPosition());
        assertEquals(MapDirection.EAST,test.getOrientation());
    }
}
