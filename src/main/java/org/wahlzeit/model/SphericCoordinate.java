package org.wahlzeit.model;

public class SphericCoordinate extends AbstractCoordinate {


    public SphericCoordinate(double phi, double theta, double radius) {
        this.x = phi;
        this.y = theta;
        this.z = radius;
    }

    @Override
    public double getDistance(Coordinate coordinate) {
        if (isSphericCoordinate(coordinate))
        {
            double a = Math.sin(((SphericCoordinate) coordinate).x) * Math.sin(this.x);
            double b = Math.cos(((SphericCoordinate) coordinate).x) * Math.sin(this.x);
            double thetaDiff = Math.abs(((SphericCoordinate) coordinate).y - z);
            return Math.acos(a + b * thetaDiff);
        }
        return 0;
    }

    @Override
    public boolean isEqual(Coordinate coordinate) {
        if (isSphericCoordinate(coordinate))
            return super.isEqual(coordinate);

        else
            return false;
    }

    private boolean isSphericCoordinate(Coordinate coordinate) {
        return coordinate instanceof SphericCoordinate;
    }

}
