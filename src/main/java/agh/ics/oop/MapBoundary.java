package agh.ics.oop;


import java.util.Comparator;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {
    private TreeSet<Vector2d> xAxis = new TreeSet<>(new Comparator<>()
    {
       @Override
       public int compare(Vector2d el1, Vector2d el2) {

           if(el1.x == el2.x)
           {
               if (el1.y == el2.y)
               {
                   return 1;
               }
               else
               {
                   return el1.y - el2.y;
               }
           }
           else
           {
               return el1.x - el2.x;
           }
       }
    });

    private TreeSet<Vector2d> yAxis = new TreeSet<>(new Comparator<>()
    {
        @Override
        public int compare(Vector2d el1, Vector2d el2) {

            if(el1.y == el2.y)
            {
                if(el1.x == el2.x)
                {
                    return 1;
                }
                else
                {
                    return el1.x - el2.x;
                }
            }
            else
            {
                return el1.y - el2.y;
            }
        }
    });

    public Vector2d getLowerLeft()
    {
        return xAxis.first().lowerLeft(yAxis.first());
    }

    public Vector2d getUpperRight()
    {
        return xAxis.last().upperRight(yAxis.last());
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        remove(oldPosition);
        add(newPosition);
    }

    public void add(Vector2d tmp)
    {
        xAxis.add(tmp);
        yAxis.add(tmp);
    }

    public void remove(Vector2d tmp)
    {
        xAxis.remove(tmp);
        yAxis.remove(tmp);
    }
}
