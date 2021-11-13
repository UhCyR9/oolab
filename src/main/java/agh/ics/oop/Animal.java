package agh.ics.oop;

public class Animal
{
    private MapDirection orientation;
    private Vector2d position;
    private IWorldMap map;

    public Animal() // Niezbyt ma sens, nie dodaje zwierzęcia do mapy
    {
        orientation = MapDirection.NORTH;
        position = new Vector2d(2,2);
    }

    public Animal(IWorldMap map)
    {
        if(map.canMoveTo(new Vector2d(0,0)))
        {
            this.map = map;
            position = new Vector2d(0,0);
            this.map.place(this);
            orientation = MapDirection.NORTH;
        }
        else
        {
            System.out.println("Position occupied");
        }
    }

    public Animal(IWorldMap map, Vector2d initialPosition)
    {
        if(map.canMoveTo(initialPosition))
        {
            this.map = map;
            position = initialPosition;
            this.map.place(this);
            orientation = MapDirection.NORTH;
        }
        else
        {
            System.out.println("Position occupied");
        }
    }


    public MapDirection getOrientation() {
        return orientation;
    }

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
                    position = tmp;
                }
            }
            case BACKWARD -> {
                tmp = position.add(orientation.tuUnitVector().opposite());
                if (map.canMoveTo(tmp)) {
                    position = tmp;
                }
            }
            default -> {
            }
        }
    }
}
