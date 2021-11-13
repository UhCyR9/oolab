package agh.ics.oop;

import java.util.LinkedList;

public class RectangularMap implements IWorldMap
{
    private Vector2d upperRight;
    private Vector2d lowerLeft = new Vector2d(0,0);
    private LinkedList<Animal> animals = new LinkedList<>();

    public RectangularMap(int width, int height)
    {
        upperRight = new Vector2d(width,height);
    }

    @Override
    public boolean canMoveTo(Vector2d position)
    {
        return position.precedes(upperRight) && position.follows(lowerLeft) && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal)
    {
        if (canMoveTo(animal.getPosition()))
        {
            animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position)
    {
        for (Animal animal : animals)
        {
            if(animal.getPosition().equals(position))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals)
        {
            if(animal.getPosition().equals(position))
            {
                return animal;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return new MapVisualizer(this).draw(lowerLeft,upperRight);
    }
}
