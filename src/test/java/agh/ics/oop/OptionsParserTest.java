package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class OptionsParserTest {

    @Test
    void parseTest() {
        OptionsParser parser = new OptionsParser();
        String[] test = {"f", "backward", "l", "right","s", "r", "left", "forward", "b", "nothing"};

        ArrayList<MoveDirection> test2 = new ArrayList<>();
        test2.add(MoveDirection.FORWARD);
        test2.add(MoveDirection.BACKWARD);
        test2.add(MoveDirection.LEFT);
        test2.add(MoveDirection.RIGHT);
        test2.add(MoveDirection.RIGHT);
        test2.add(MoveDirection.LEFT);
        test2.add(MoveDirection.FORWARD);
        test2.add(MoveDirection.BACKWARD);

        assertEquals(test2, parser.parse(test));

    }
}
