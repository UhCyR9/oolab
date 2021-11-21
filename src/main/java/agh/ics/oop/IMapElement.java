package agh.ics.oop;

public interface IMapElement {

    Vector2d getPosition();

    default boolean isAt(Vector2d position) {
        return this.getPosition().equals(position);
    }
}
