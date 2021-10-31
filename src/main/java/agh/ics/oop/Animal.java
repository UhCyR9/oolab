package agh.ics.oop;

public class Animal
{
    private MapDirection orientation;
    private Vector2d position;

    public Animal()
    {
        orientation = MapDirection.NORTH;
        position = new Vector2d(2,2);
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }

    public String toString()
    {
        return "Pozycja: " + position.toString() + ", Orientacja: " + orientation.toString();
    }

    public void move(MoveDirection direction)
    {
        Vector2d tmp;
        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> {
                tmp = position.add(orientation.tuUnitVector());
                if (tmp.precedes(new Vector2d(4, 4)) && tmp.follows(new Vector2d(0, 0))) {
                    position = tmp;
                }
            }
            case BACKWARD -> {
                tmp = position.add(orientation.tuUnitVector().opposite());
                if (tmp.precedes(new Vector2d(4, 4)) && tmp.follows(new Vector2d(0, 0))) {
                    position = tmp;
                }
            }
            default -> {
            }
        }
    }
}
