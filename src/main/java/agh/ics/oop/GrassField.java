package agh.ics.oop;

import java.util.Hashtable;
import java.util.Random;

import static java.lang.Math.*;


public class GrassField extends AbstractWorldMap implements IWorldMap{
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

            lowerLeft = lowerLeft.lowerLeft(tmp);
            upperRight = upperRight.upperRight(tmp);

            passable.put(tmp,new Grass(tmp));
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position)
    {
        if (!(position.follows(new Vector2d(0,0))))
        {
            return false;
        }
        for (IMapElement element : impassable)
        {
            if(element.getPosition().equals(position))
            {
                return false;
            }
        }
        return true;
    }

    public void updateBoundaries() {
        for (IMapElement element : impassable)
        {
            lowerLeft = lowerLeft.lowerLeft(element.getPosition());
            upperRight = upperRight.upperRight(element.getPosition());
        }
    }
}
