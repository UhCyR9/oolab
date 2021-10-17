package agh.ics.oop;

public class World {
    public static void main(String[] args)
    {
        System.out.println("Start");
        run(args);
        System.out.println("Stop");
    }

    public static void run(String[] directions)
    {
        System.out.println("Zwierzak idzie do przodu");
        for (int i = 0; i < directions.length;i++)
        {
            if (i != 0)
            {
                System.out.print(", " + directions[i]);
            }
            else
            {
                System.out.print(directions[i]);
            }
        }
        System.out.println();

        for (String direction : directions)
        {
            switch (direction) {
                case "f" -> System.out.println("Zwierzak idzie do przodu");
                case "b" -> System.out.println("Zwierzak idzie do tylu");
                case "r" -> System.out.println("Zwierzak idzie w prawo");
                case "l" -> System.out.println("Zwierzak idzie w lewo");
                default -> {
                }
            }

        }
    }
}
