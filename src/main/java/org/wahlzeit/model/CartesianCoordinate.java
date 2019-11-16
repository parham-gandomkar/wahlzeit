package org.wahlzeit.model;

public class CartesianCoordinate implements Coordinate {

    private double x;
    private double y;
    private double z;

    public CartesianCoordinate(double x, double y,double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public Coordinate asCoordinate() {
        return this;
    }

    @Override
    public double getDistance(Coordinate coordinate) {
        if (isCartesianCoordinate(coordinate))
        {
            return Math.sqrt(Math.pow(x - ((CartesianCoordinate) coordinate).x, 2) +
                    Math.pow(y - ((CartesianCoordinate) coordinate).y, 2) +
                    Math.pow(z - ((CartesianCoordinate) coordinate).z, 2));
        }
        return 0;
    }

    @Override
    public boolean isEqual(Coordinate coordinate) {

        if (isNull(coordinate)) return false;
        if (coordinate == this)
            return true;
        if (!isCartesianCoordinate(coordinate)) {
            return false;
        }
        return (((CartesianCoordinate) coordinate).x == x &&
                ((CartesianCoordinate) coordinate).y == y &&
                ((CartesianCoordinate) coordinate).z == z);
    }

    private boolean isCartesianCoordinate(Coordinate coordinate) {
        return coordinate instanceof CartesianCoordinate;
    }

    private boolean isNull(Coordinate coordinate) {
        return coordinate == null;
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(String.valueOf(x) + String.valueOf(y) + String.valueOf(z));
    }
}
