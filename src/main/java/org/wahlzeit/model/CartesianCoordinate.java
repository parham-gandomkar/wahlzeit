package org.wahlzeit.model;

public class CartesianCoordinate extends AbstractCoordinate {


    public CartesianCoordinate(double x, double y,double z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
        if(isCartesianCoordinate(coordinate))
            return super.isEqual(coordinate);
        else
            return false;
    }

    private boolean isCartesianCoordinate(Coordinate coordinate) {
        return coordinate instanceof CartesianCoordinate;
    }



}
