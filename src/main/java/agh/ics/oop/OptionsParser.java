package agh.ics.oop;

import java.util.ArrayList;

public class OptionsParser {

    public ArrayList<MoveDirection> parse(String[] directions)
    {
        ArrayList<MoveDirection> result = new ArrayList<>();
        for (String direction : directions)
        {
            switch (direction) {
                case "f", "forward" -> result.add(MoveDirection.FORWARD);
                case "b", "backward" -> result.add(MoveDirection.BACKWARD);
                case "l", "left" -> result.add(MoveDirection.LEFT);
                case "r", "right" -> result.add(MoveDirection.RIGHT);
                default -> {
                }
            }
        }

        return  result;
    }
}
