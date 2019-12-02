package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate{

    double x;
    double y;
    double z;

    @Override
    public Coordinate asCartesianCoordinate() {
        return this;
    }

    @Override
    public Coordinate asSphericCoordinate() {
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

    // I defined the helper methods
    // in parents.
    // then we don't need to write this method for each child
    // and have redundant code
    public Coordinate convertSphericalToCartesian(Coordinate coordinate) {
        if (!isCartesianCoordinate(coordinate)) {
            float x = (float) (((SphericCoordinate) coordinate).z * Math.cos(((SphericCoordinate) coordinate).y)
                    * Math.sin(((SphericCoordinate) coordinate).x));
            float y = (float) (((SphericCoordinate) coordinate).z * Math.sin(((SphericCoordinate) coordinate).x)
                    * Math.sin(((SphericCoordinate) coordinate).y));
            float z = (float) (((SphericCoordinate) coordinate).z * Math.cos(((SphericCoordinate) coordinate).x));
            return new CartesianCoordinate(x, y, z);
        }
        return coordinate;
    }
    public Coordinate convertCartesianToSpherical(Coordinate coordinate) {
        if (!isSphericCoordinate(coordinate)) {
            float r = (float) Math.sqrt(((CartesianCoordinate) coordinate).x * ((CartesianCoordinate) coordinate).x +
                    ((CartesianCoordinate) coordinate).y * ((CartesianCoordinate) coordinate).y
                    + ((CartesianCoordinate) coordinate).z * ((CartesianCoordinate) coordinate).z);
            float teta = (float) Math.atan2(((CartesianCoordinate) coordinate).y, ((CartesianCoordinate) coordinate).x);
            float phi = (float) Math.acos(((CartesianCoordinate) coordinate).z / r);
            return new SphericCoordinate(phi, teta, r);
        }
        return coordinate;
    }
    // helper methods for calculating distance and central Angle
    public double calculateDistance(CartesianCoordinate coordinate, CartesianCoordinate cartesianCoordinate) {
        return Math.sqrt(Math.pow(cartesianCoordinate.x - coordinate.x, 2) +
                Math.pow(cartesianCoordinate.y - coordinate.y, 2) +
                Math.pow(cartesianCoordinate.z - coordinate.z, 2));
    }
    public double calculateCentralAngle(SphericCoordinate coordinate, SphericCoordinate sphericCoordinate) {
        double a = Math.sin(((SphericCoordinate) coordinate).x * Math.sin(sphericCoordinate.x));
        double b = Math.cos(((SphericCoordinate) coordinate).x * Math.sin(sphericCoordinate.x));
        double thetaDiff = Math.abs(((SphericCoordinate) coordinate).y - sphericCoordinate.z);
        return   Math.acos(a + b * thetaDiff);
    }

    // helper methods and assert methods
    public boolean isSphericCoordinate(Coordinate coordinate) {
        return coordinate instanceof SphericCoordinate;
    }
    public boolean isCartesianCoordinate(Coordinate coordinate) {
        return coordinate instanceof CartesianCoordinate;
    }

    public boolean isCoordinateValid(Coordinate coordinate) {
        return coordinate != null;
    }
    public boolean isDistanceGreaterThanZero(double distance){
        return distance >= 0;
    }
}
