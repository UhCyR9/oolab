package agh.ics.oop;

import java.util.Hashtable;
import java.util.LinkedList;

public abstract class AbstractWorldMap implements IWorldMap {
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;
    protected LinkedList<IMapElement> impassable = new LinkedList<>();
    protected Hashtable<Vector2d, IMapElement> passable = new Hashtable<>();

    public abstract boolean canMoveTo(Vector2d position);

    public LinkedList<IMapElement> getImpassable() {
        return impassable;
    }

    public Hashtable<Vector2d, IMapElement> getPassable() {
        return passable;
    }

    @Override
    public boolean place(Animal animal)
    {
        if (canMoveTo(animal.getPosition()))
        {
            impassable.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position)
    {
        if (passable.containsKey(position))
        {
            return true;
        }

        for (IMapElement element : impassable)
        {
            if(element.getPosition().equals(position))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (IMapElement element : impassable)
        {
            if(element.getPosition().equals(position))
            {
                return element;
            }
        }

        if (passable.containsKey(position))
        {
            return passable.get(position);
        }

        return null;
    }

    public abstract void updateBoundaries();

    @Override
    public String toString() {
        this.updateBoundaries();
        return new MapVisualizer(this).draw(lowerLeft, upperRight);
    }
}
