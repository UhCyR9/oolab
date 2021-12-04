package agh.ics.oop;


import java.util.Comparator;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {
    private IWorldMap map;
    private TreeSet<IMapElement> xAxis = new TreeSet<>(new Comparator<IMapElement>()
    {
       @Override
       public int compare(IMapElement el1, IMapElement el2) {
           Vector2d el1Position = el1.getPosition();
           Vector2d el2Position = el2.getPosition();

           if(el1Position.x == el2Position.x)
           {
               return el1Position.y - el2Position.y;
           }
           else
           {
               return el1Position.x - el2Position.x;
           }
       }
    });

    private TreeSet<IMapElement> yAxis = new TreeSet<>(new Comparator<IMapElement>()
    {
        @Override
        public int compare(IMapElement el1, IMapElement el2) {
            Vector2d el1Position = el1.getPosition();
            Vector2d el2Position = el2.getPosition();

            if(el1Position.y == el2Position.y)
            {
                return el1Position.x - el2Position.x;
            }
            else
            {
                return el1Position.y - el2Position.y;
            }
        }
    });

    public MapBoundary(IWorldMap map)
    {
        this.map = map;
    }

    public Vector2d getLowerLeft()
    {
        return xAxis.first().getPosition().lowerLeft(yAxis.first().getPosition());
    }

    public Vector2d getUpperRight()
    {
        return xAxis.last().getPosition().upperRight(yAxis.last().getPosition());
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        IMapElement element = (IMapElement)this.map.objectAt(newPosition);
        this.remove(element);
        this.add(element);
    }

    public void add(IMapElement element)
    {
        xAxis.add(element);
        yAxis.add(element);
    }

    public void remove(IMapElement element)
    {
        xAxis.remove(element);
        yAxis.remove(element);
    }
}
