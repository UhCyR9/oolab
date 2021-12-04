package agh.ics.oop;

import java.util.Hashtable;
import java.util.Random;

import static java.lang.Math.*;


public class GrassField extends AbstractWorldMap{
    private int grassAmount;

    public GrassField(int amount)
    {
        lowerLeft = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        upperRight = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        grassAmount = amount;
        int maxXY = (int)sqrt(amount*10)+1;
        Random random = new Random();
        for (int i = 0; i < amount; i++)
        {
            Vector2d tmp = new Vector2d(random.nextInt(maxXY),random.nextInt(maxXY));
            while (passable.containsKey(tmp))
            {
                tmp = new Vector2d(random.nextInt(maxXY),random.nextInt(maxXY));
            }

            Grass grass = new Grass(tmp);
            mapBoundary.add(grass);
            passable.put(tmp,grass);
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position)
    {
        return !impassable.containsKey(position);
    }
}
