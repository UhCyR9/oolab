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
        return (position.precedes(upperRight) && position.follows(lowerLeft)) && !impassable.containsKey(position);
    }

    @Override
    public void updateBoundaries() {}
}
