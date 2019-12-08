package org.wahlzeit.model;

import org.wahlzeit.exceptions.InvalidCoordinateClassTypeException;
import org.wahlzeit.exceptions.InvalidDistanceException;

public abstract class AbstractCoordinate implements Coordinate{

    double x;
    double y;
    double z;

    @Override
    public Coordinate asCartesianCoordinate() throws InvalidCoordinateClassTypeException {
        return this;
    }

    @Override
    public Coordinate asSphericalCoordinate() throws InvalidCoordinateClassTypeException {
        return this;
    }

    @Override
    public boolean isEqual(Coordinate coordinate) {
        if (isNull(coordinate)) return false;
        if (coordinate == this)
            return true;
        // for comparing two double variable,
        // i used an approximation for checking equality
        return ((Math.abs(((AbstractCoordinate) coordinate).x - x) < 0.1) &&
                (Math.abs(((AbstractCoordinate) coordinate).y - y) < 0.1) &&
                (Math.abs(((AbstractCoordinate) coordinate).z - z)) < 0.1);
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
            float x = (float) (((SphericalCoordinate) coordinate).z * Math.cos(((SphericalCoordinate) coordinate).y)
                    * Math.sin(((SphericalCoordinate) coordinate).x));
            float y = (float) (((SphericalCoordinate) coordinate).z * Math.sin(((SphericalCoordinate) coordinate).x)
                    * Math.sin(((SphericalCoordinate) coordinate).y));
            float z = (float) (((SphericalCoordinate) coordinate).z * Math.cos(((SphericalCoordinate) coordinate).x));
            return new CartesianCoordinate(x, y, z);
        }
        return coordinate;
    }
    public Coordinate convertCartesianToSpherical(Coordinate coordinate) {
        if (!isSphericalCoordinate(coordinate)) {
            float r = (float) Math.sqrt(((CartesianCoordinate) coordinate).x * ((CartesianCoordinate) coordinate).x +
                    ((CartesianCoordinate) coordinate).y * ((CartesianCoordinate) coordinate).y
                    + ((CartesianCoordinate) coordinate).z * ((CartesianCoordinate) coordinate).z);
            float teta = (float) Math.atan2(((CartesianCoordinate) coordinate).y, ((CartesianCoordinate) coordinate).x);
            float phi = (float) Math.acos(((CartesianCoordinate) coordinate).z / r);
            return new SphericalCoordinate(phi, teta, r);
        }
        return coordinate;
    }
    // helper methods for calculating distance and central Angle
    public double calculateDistance(CartesianCoordinate coordinate, CartesianCoordinate cartesianCoordinate) {
        return Math.sqrt(Math.pow(cartesianCoordinate.x - coordinate.x, 2) +
                Math.pow(cartesianCoordinate.y - coordinate.y, 2) +
                Math.pow(cartesianCoordinate.z - coordinate.z, 2));
    }
    public double calculateCentralAngle(SphericalCoordinate coordinate, SphericalCoordinate sphericalCoordinate) {
        double a = Math.sin(((SphericalCoordinate) coordinate).x * Math.sin(sphericalCoordinate.x));
        double b = Math.cos(((SphericalCoordinate) coordinate).x * Math.sin(sphericalCoordinate.x));
        double thetaDiff = Math.abs(((SphericalCoordinate) coordinate).y - sphericalCoordinate.z);
        return   Math.acos(a + b * thetaDiff);
    }

    // helper methods and assert methods
    public boolean isSphericalCoordinate(Coordinate coordinate) {
        return coordinate instanceof SphericalCoordinate;
    }
    public boolean isCartesianCoordinate(Coordinate coordinate) {
        return coordinate instanceof CartesianCoordinate;
    }

    public void assertIsCoordinateValid(Coordinate coordinate) throws NullPointerException {
        if(coordinate == null){
            throw new NullPointerException();
        }
        assertIsDoubleValueValid(((AbstractCoordinate)coordinate).x);
        assertIsDoubleValueValid(((AbstractCoordinate)coordinate).y);
        assertIsDoubleValueValid(((AbstractCoordinate)coordinate).z);
    }

    protected void assertIsDoubleValueValid(double x){
        if(Double.isInfinite(x)){
            throw new IllegalArgumentException("Overflow!");
        }
        if(Double.isNaN(x)) {
            throw new IllegalArgumentException("NaN!");
        }
    }

    public void assertIsDistanceGreaterThanZero(double distance) throws InvalidDistanceException {
        if(distance < 0){
            throw new InvalidDistanceException();
        }
    }

    public void assertIsCartesianCoordinate(Coordinate coordinate) throws InvalidCoordinateClassTypeException {
        if(coordinate instanceof CartesianCoordinate){
            // error escalation
            convertSphericalToCartesian(coordinate);
        }
        else {
            // error signaling
            throw new InvalidCoordinateClassTypeException("coordinate class is not cartesian");
        }
    }

    public void assertIsSphericalCoordinate(Coordinate coordinate) throws InvalidCoordinateClassTypeException {
        if(coordinate instanceof SphericalCoordinate){
            // error escalation
            convertCartesianToSpherical(coordinate);
        }
        else {
            // error signaling
            throw new InvalidCoordinateClassTypeException("coordinate class is not Spherical");
        }
    }

}
