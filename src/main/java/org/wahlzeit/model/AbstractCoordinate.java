package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate{

    double x;
    double y;
    double z;

    @Override
    public Coordinate asCoordinate() {
        return this;
    }


    @Override
    public boolean isEqual(Coordinate coordinate) {
        if (isNull(coordinate)) return false;
        if (coordinate == this)
            return true;

        return (((AbstractCoordinate) coordinate).x == x &&
                ((AbstractCoordinate) coordinate).y == y &&
                ((AbstractCoordinate) coordinate).z == z);
    }

    private boolean isNull(Coordinate coordinate) {
        return coordinate == null;
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(String.valueOf(x) + String.valueOf(y) + String.valueOf(z));
    }
}
