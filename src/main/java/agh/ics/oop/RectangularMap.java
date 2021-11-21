package agh.ics.oop;

public class RectangularMap extends AbstractWorldMap implements IWorldMap
{
    public RectangularMap(int width, int height)
    {
        upperRight = new Vector2d(width,height);
        lowerLeft = new Vector2d(0,0);
    }

    @Override
    public boolean canMoveTo(Vector2d position)
    {
        if (!(position.precedes(upperRight) && position.follows(lowerLeft)))
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

    @Override
    public void updateBoundaries() {}
}
