package agh.ics.oop;

import java.util.ArrayList;

public class World {
    public static void main(String[] args)
    {
        System.out.println("Start");

        ArrayList<MoveDirection> directions = new OptionsParser().parse(args);
        IWorldMap map = new GrassField(10);
        System.out.println(map);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        System.out.println(map);

        System.out.println("Stop");
    }


    public static void run(ArrayList<Direction> directions)
    {
        for (Direction dir : directions)
        {
            switch (dir) {
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tylu");
                case RIGHT -> System.out.println("Zwierzak idzie w prawo");
                case LEFT -> System.out.println("Zwierzak idzie w lewo");
                default -> {
                }
            }
        }
    }
}
