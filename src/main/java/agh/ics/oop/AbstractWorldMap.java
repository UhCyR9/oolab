package agh.ics.oop;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IPositionChangeObserver, IWorldMap {
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;
    protected Map<Vector2d,IMapElement> impassable = new LinkedHashMap<>();
    protected Map<Vector2d, IMapElement> passable = new HashMap<>();
    protected MapBoundary mapBoundary = new MapBoundary();

    public abstract boolean canMoveTo(Vector2d position);

    public Map<Vector2d,IMapElement> getImpassable() {
        return impassable;
    }

    public Map<Vector2d, IMapElement> getPassable() {
        return passable;
    }

    public Vector2d getLowerLeft()
    {
        this.updateBoundaries();
        return lowerLeft;
    }

    public Vector2d getUpperRight()
    {
        this.updateBoundaries();
        return upperRight;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        if (impassable.containsKey(oldPosition))
        {
            IMapElement tmp = impassable.get(oldPosition);
            impassable.put(newPosition, tmp);
            impassable.remove(oldPosition);
            mapBoundary.positionChanged(oldPosition,newPosition);
        }
    }

    @Override
    public boolean place(Animal animal)
    {
        if (canMoveTo(animal.getPosition()))
        {
            mapBoundary.add(animal.getPosition());
            impassable.put(animal.getPosition(), animal);
            animal.addObserver(this);
            return true;
        }
        throw new IllegalArgumentException("Position " + animal.getPosition() + " is already occupied");
    }

    @Override
    public boolean isOccupied(Vector2d position)
    {
        return passable.containsKey(position) || impassable.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (impassable.containsKey(position))
        {
            return impassable.get(position);
        }

        if (passable.containsKey(position))
        {
            return passable.get(position);
        }

        return null;
    }

    public void updateBoundaries() {
        lowerLeft = mapBoundary.getLowerLeft();
        upperRight = mapBoundary.getUpperRight();
    }

    @Override
    public String toString() {
        this.updateBoundaries();
        return new MapVisualizer(this).draw(lowerLeft, upperRight);
    }
}
