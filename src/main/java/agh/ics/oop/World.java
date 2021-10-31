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
        OptionsParser parser = new OptionsParser();
        ArrayList<MoveDirection> moves = parser.parse(args);

        Animal snake = new Animal();
        for (MoveDirection move : moves)
        {
            snake.move(move);
            System.out.println(snake);
        }

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
