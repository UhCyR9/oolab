package agh.ics.oop;


import java.util.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static agh.ics.oop.MapDirection.*;
import static agh.ics.oop.MoveDirection.*;

public class SimulationEngineTest {

    @Test
    void run()
    {
        String[] args = {"f", "b", "r", "l"};
        ArrayList<MoveDirection> directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        ArrayList<Animal> animals = engine.getAnimals();

        // czy zwierzęta dobrze się poruszają w metodzie run w SimulationEngine
        assertEquals(EAST,animals.get(0).getOrientation());
        assertEquals(WEST,animals.get(1).getOrientation());
        assertEquals(new Vector2d(2,3),animals.get(0).getPosition());
        assertEquals(new Vector2d(3,3),animals.get(1).getPosition());

        // czy zwierzęta nie mogą na siebie wejść
        animals.get(0).move(FORWARD);
        assertEquals(new Vector2d(2,3),animals.get(0).getPosition());

        // czy zwierzęta nie wychodzą za granice mapy
        animals.get(0).move(LEFT);
        animals.get(0).move(FORWARD);
        animals.get(0).move(FORWARD);
        animals.get(0).move(FORWARD);
        animals.get(0).move(FORWARD);
        animals.get(0).move(FORWARD);
        assertEquals(new Vector2d(2,5),animals.get(0).getPosition());

        animals.get(1).move(RIGHT);
        animals.get(1).move(BACKWARD);
        animals.get(1).move(BACKWARD);
        animals.get(1).move(BACKWARD);
        animals.get(1).move(BACKWARD);
        animals.get(1).move(BACKWARD);
        assertEquals(new Vector2d(3,0),animals.get(1).getPosition());

        // Czy zwierzęta dobrze aktualizuja pozycje względem mapy
        assertEquals(map.objectAt(new Vector2d(2,5)),animals.get(0));
        assertEquals(map.objectAt(new Vector2d(3,0)),animals.get(1));
    }
}
