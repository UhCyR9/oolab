package agh.ics.oop;

import java.util.ArrayList;

public class World {
    public static void main(String[] args)
    {
        System.out.println("Start");
        ArrayList<Direction> directions = new ArrayList<>();

        for (String arg : args)
        {
            switch (arg) {
                case "f" -> directions.add(Direction.FORWARD);
                case "b" -> directions.add(Direction.BACKWARD);
                case "r" -> directions.add(Direction.RIGHT);
                case "l" -> directions.add(Direction.LEFT);
                default -> {
                }
            }
        }
        run(directions);
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
        System.out.println();


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
