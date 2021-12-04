package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedList;

public class Animal implements IMapElement
{
    private MapDirection orientation;
    private Vector2d position;
    private IWorldMap map;
    private ArrayList<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal() // Niezbyt ma sens, nie dodaje zwierzęcia do mapy
    {
        orientation = MapDirection.NORTH;
        position = new Vector2d(2,2);
    }

    public Animal(IWorldMap map)
    {
            this.map = map;
            position = new Vector2d(0,0);
            this.map.place(this);
            orientation = MapDirection.NORTH;
    }

    public Animal(IWorldMap map, Vector2d initialPosition)
    {
            this.map = map;
            position = initialPosition;
            this.map.place(this);
            orientation = MapDirection.NORTH;
    }


    public MapDirection getOrientation() {
        return orientation;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    public IWorldMap getMap(){
        return map;
    }

    @Override
    public String toString()
    {
        switch (orientation)
        {
            case NORTH -> {return "N";}
            case EAST -> {return "E";}
            case SOUTH -> {return "S";}
            case WEST -> {return "W";}
        }
        return null;
    }

    public void move(MoveDirection direction)
    {
        Vector2d tmp;
        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> {
                tmp = position.add(orientation.tuUnitVector());
                if (map.canMoveTo(tmp)) {
                    positionChanged(this.position,tmp);
                    position = tmp;
                }
            }
            case BACKWARD -> {
                tmp = position.add(orientation.tuUnitVector().opposite());
                if (map.canMoveTo(tmp)) {
                    positionChanged(this.position,tmp);
                    position = tmp;
                }
            }
            default -> {
            }
        }
    }

    public void addObserver(IPositionChangeObserver observer)
    {
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer)
    {
        observers.remove(observer);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition)
    {
        for (IPositionChangeObserver observer : observers)
        {
            observer.positionChanged(oldPosition, newPosition);
        }
    }
}
