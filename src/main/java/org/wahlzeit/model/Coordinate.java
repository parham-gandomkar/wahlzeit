package org.wahlzeit.model;

public class Coordinate {
    public static final Coordinate EMPTY = new Coordinate(0.0,0.0, 0.0);

    private double x;
    private double y;
    private double z;

    public Coordinate(double x, double y,double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    public double getDistance(Coordinate coordinate) {
        return Math.sqrt(Math.pow(x - coordinate.x, 2) +
                Math.pow(y - coordinate.y, 2) +
                Math.pow(z - coordinate.z, 2));
    }

    public boolean isEqual(Coordinate coordinate) {
        if (coordinate.x == x &&
                coordinate.y == y &&
                coordinate.z == z) {
            return true;
        }
        return false;
    }
}
