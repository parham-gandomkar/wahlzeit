package org.wahlzeit.model;

public class SphericCoordinate implements Coordinate {

    private double phi;
    private double theta;
    private double radius;

    public SphericCoordinate(double phi, double theta, double radius) {
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
    }

    @Override
    public Coordinate asCoordinate() {
        return this;
    }

    @Override
    public double getDistance(Coordinate coordinate) {
        if (isSphericCoordinate(coordinate))
        {
            double x = Math.sin(((SphericCoordinate) coordinate).phi) * Math.sin(phi);
            double y = Math.cos(((SphericCoordinate) coordinate).phi) * Math.sin(phi);
            double thetaDiff = Math.abs(((SphericCoordinate) coordinate).theta - theta);
            return Math.acos(x + y * thetaDiff);
        }
        return 0;
    }


    @Override
    public boolean isEqual(Coordinate coordinate) {

        if (isNull(coordinate)) return false;
        if (coordinate == this)
            return true;
        if (!isSphericCoordinate(coordinate)) {
            return false;
        }
        return (((SphericCoordinate) coordinate).radius == radius &&
                ((SphericCoordinate) coordinate).theta == theta &&
                ((SphericCoordinate) coordinate).phi == phi);
    }

    private boolean isSphericCoordinate(Coordinate coordinate) {
        return coordinate instanceof SphericCoordinate;
    }

    private boolean isNull(Coordinate coordinate) {
        return coordinate == null;
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(String.valueOf(phi) + String.valueOf(theta) + String.valueOf(radius));
    }
}
