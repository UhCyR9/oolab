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
        System.out.println("Stop");
    }

    public static void run(ArrayList<Direction> directions)
    {
//        System.out.println("Zwierzak idzie do przodu");
//        for (int i = 0; i < directions.length;i++)
//        {
//            if (i != 0)
//            {
//                System.out.print(", " + directions[i]);
//            }
//            else
//            {
//                System.out.print(directions[i]);
//            }
//        }
//        System.out.println();

//        for (String direction : directions)
//        {
//            switch (direction) {
//                case "f" -> System.out.println("Zwierzak idzie do przodu");
//                case "b" -> System.out.println("Zwierzak idzie do tylu");
//                case "r" -> System.out.println("Zwierzak idzie w prawo");
//                case "l" -> System.out.println("Zwierzak idzie w lewo");
//                default -> {
//                }
//            }
//        }

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
